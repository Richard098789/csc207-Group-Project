package api;

import java.io.IOException;
import entity.Artist;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MusicBrainzAPI {
    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String FORMAT = "fmt=json";
    private static final String USER_AGENT = "MusicRating/1.0.0 (delfen.gamma@gmail.com)";
    private final OkHttpClient client;

    // Constructor
    public MusicBrainzAPI() {
        this.client = new OkHttpClient.Builder().build();
    }

    /**
     * Fetches artists with pagination support.
     *
     * @param artistName Name of the artist to search for.
     * @param limit      Number of results to fetch per request.
     * @param offset     Offset for paginated requests.
     * @return Array of Artist objects.
     */
    public Artist[] getArtistsPaginated(String artistName, int limit, int offset) {
        try {
            // URL encode the artist name to handle special characters
            String encodedArtistName = URLEncoder.encode(artistName, StandardCharsets.UTF_8);

            // Build the API URL without type or country filters
            String url = String.format(
                    "%s/artist/?query=artist:%s&%s&limit=%d&offset=%d",
                    BASE_URL, encodedArtistName, FORMAT, limit, offset
            );

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("User-Agent", USER_AGENT)
                    .build();

            System.out.println("Fetching artists from: " + url);

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected response code: " + response.code());
                }

                String responseBodyString = response.body() != null ? response.body().string() : "";
                JSONObject responseBody = new JSONObject(responseBodyString);
                JSONArray artists = responseBody.optJSONArray("artists");

                if (artists == null || artists.length() == 0) {
                    System.out.println("No artists found for the query.");
                    return new Artist[0];
                }

                Artist[] result = new Artist[artists.length()];
                for (int i = 0; i < artists.length(); i++) {
                    JSONObject artist = artists.getJSONObject(i);
                    result[i] = parseArtist(artist);
                }
                return result;
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Failed to fetch artists: " + e.getMessage(), e);
        }
    }

    /**
     * Parses a JSONObject to create an Artist object.
     */
    private Artist parseArtist(JSONObject artist) {
        return Artist.builder()
                .artistName(artist.optString("name", "Unknown"))
                .type(artist.optString("type", "N/A"))
                .score(artist.optInt("score", 0))
                .country(artist.optString("country", "N/A"))
                .isDead(artist.optJSONObject("life-span") != null &&
                        artist.optJSONObject("life-span").optBoolean("ended", false))
                .build();
    }
}
