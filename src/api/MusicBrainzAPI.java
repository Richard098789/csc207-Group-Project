package api;
import java.io.IOException;
import entity.Artist;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

            System.out.println("Response Body: " + responseBody);

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
//    Below is a test case
    public static void main(String[] args) {
        MusicBrainzAPI api = new MusicBrainzAPI();
        Artist[] artists = api.getArtists("richard", "person", "US");
        System.out.println(artists.length);
    }
}


