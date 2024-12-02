package Use_case.event_search;

import data_transfer_object.Event;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class MusicBrainzEventRepository {

    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String FORMAT = "fmt=json";

    public Event[] getEvents(String eventName, String location, int limit, int offset) {
        OkHttpClient client = new OkHttpClient();
        StringBuilder queryBuilder = new StringBuilder(BASE_URL + "/event/?query=");

        if (eventName != null && !eventName.isEmpty()) {
            queryBuilder.append("event:").append(eventName).append(" ");
        }
        if (location != null && !location.isEmpty()) {
            queryBuilder.append("AND location:").append(location).append(" ");
        }

        queryBuilder.append(String.format("&%s&limit=%d&offset=%d", FORMAT, limit, offset));

        try {
            Request request = new Request.Builder()
                    .url(queryBuilder.toString())
                    .addHeader("User-Agent", "MusicRating/1.0.0 (delfen.gamma@gmail.com)")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray events = responseBody.getJSONArray("events");

            Event[] result = new Event[events.length()];
            for (int i = 0; i < events.length(); i++) {
                JSONObject event = events.getJSONObject(i);

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

                String begin = "Unknown";
                String end = "Unknown";
                // Check and extract begin and end information
                if (event.has("life-span")) {
                    final JSONObject lifespan = event.getJSONObject("life-span");

                    if (lifespan.has("begin")) {
                        begin = lifespan.optString("begin", "Unknown");
                    }

                    if (lifespan.has("end")) {
                        end = lifespan.optString("end", "Unknown");
                    }
                }

                // Build the Event object with all extracted data
                result[i] = Event.builder()
                        .id(event.optString("id", UUID.randomUUID().toString()))
                        .name(event.getString("name"))
                        .type(event.optString("type", "N/A"))
                        .beginDate(begin)
                        .endDate(end)
                        .time(event.optString("time", "N/A"))
                        .placeName(placeName)
                        .placeId(placeId)
                        .artistName(artistName)
                        .artistId(artistId)
                        .score(event.optInt("score", 0))
                        .build();
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching events: " + e.getMessage());
        }
    }
}
