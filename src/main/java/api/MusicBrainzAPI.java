package api;
import java.io.IOException;
import entity.Artist;
import entity.Event;
// import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
// import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
// import org.w3c.dom.events.Event;

public class MusicBrainzAPI {
    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String MAX_ITEM = "limit=10";
    private static final String FORMAT = "fmt=json";

    public Artist[] getArtists(String artistName, String type, String country) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s/artist/?query=artist:%s AND type:%s AND country:%s&%s",
                        BASE_URL, artistName, type, country, FORMAT))
                .addHeader("User-Agent", "MusicRating/1.0.0 ( delfen.gamma@gmail.com )")
                .build();
        System.out.println(request.url());
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            final JSONArray artists = responseBody.getJSONArray("artists");
            final Artist[] result = new Artist[artists.length()];
            for (int i = 0; i < artists.length(); i++) {
                final JSONObject artist = artists.getJSONObject(i);
                result[i] = Artist.builder()
                        .artistName(artist.getString("name"))
                        .type(artist.getString("type"))
                        .score(artist.getInt("score"))
                        .country(artist.getString("country"))
                        .isDead(artist.getJSONObject("life-span").optBoolean("ended"))
                        .build();
            }
            return result;
        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

    public Event[] getEvents(String eventName, String type, String area) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("%s/event/?query=event:%s AND type:%s AND area:%s&%s",
                        BASE_URL, eventName, type, area, FORMAT))
                .addHeader("User-Agent", "MusicRating/1.0.0 ( delfen.gamma@gmail.com )")
                .build();
        System.out.println(request.url());
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            final JSONArray events = responseBody.getJSONArray("events");
            final Event[] result = new Event[events.length()];
            for (int i = 0; i < events.length(); i++) {
                final JSONObject event = events.getJSONObject(i);

                // Initialize placeholders for optional fields with default values
                String placeName = "Unknown";
                String placeId = "";
                String artistName = "Unknown";
                String artistId = "";

                // Check if "relations" array exists and has the expected objects
                if (event.has("relations")) {
                    final JSONArray relations = event.getJSONArray("relations");
                    for (int j = 0; j < relations.length(); j++) {
                        final JSONObject relation = relations.getJSONObject(j);

                        // Check and extract place information if available
                        if (relation.has("place")) {
                            JSONObject place = relation.optJSONObject("place");
                            if (place != null) {
                                placeName = place.optString("name", "Unknown");
                                placeId = place.optString("id", "");
                            }
                        }

                        // Check and extract artist information if available
                        if (relation.has("artist")) {
                            JSONObject artist = relation.optJSONObject("artist");
                            if (artist != null) {
                                artistName = artist.optString("name", "Unknown");
                                artistId = artist.optString("id", "");
                            }
                        }
                    }
                }

                // Build the Event object with all extracted data
                result[i] = Event.builder()
                        .id(event.getString("id"))
                        .name(event.getString("name"))
                        .type(event.optString("type", "N/A"))
                        .beginDate(event.getJSONObject("life-span").optString("begin"))
                        .endDate(event.getJSONObject("life-span").optString("end"))
                        .time(event.optString("time", "N/A"))
                        .placeName(placeName)
                        .placeId(placeId)
                        .artistName(artistName)
                        .artistId(artistId)
                        .score(event.optInt("score", 0))
                        .build();
                }
            return result;
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    
//    Below is a test case
    public static void main(String[] args) {
        MusicBrainzAPI api = new MusicBrainzAPI();
        // Artist[] artists = api.getArtists("richard", "person", "US");
        // System.out.println(artists.length);
        Event[] events = api.getEvents("Jazz Fest", "festival", "new york");
        System.out.println(events[2].getId());
    }
}


