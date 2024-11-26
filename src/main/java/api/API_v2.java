package api;

import entity.Artist;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class API_v2 {
    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String FORMAT = "fmt=json";

    public Artist[] getArtists(String artistName, String country, int limit, int offset) {
        OkHttpClient client = new OkHttpClient();
        StringBuilder queryBuilder = new StringBuilder(BASE_URL + "/artist/?query=");

        if (!artistName.isEmpty()) {
            queryBuilder.append("artist:").append(artistName).append(" ");
        }
        if (!country.isEmpty()) {
            queryBuilder.append("AND country:").append(country).append(" ");
        }

        queryBuilder.append(String.format("&%s&limit=%d&offset=%d", FORMAT, limit, offset));

        try {
            Request request = new Request.Builder()
                    .url(queryBuilder.toString())
                    .addHeader("User-Agent", "MusicRating/1.0.0 (delfen.gamma@gmail.com)")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONArray artists = responseBody.getJSONArray("artists");

            Artist[] result = new Artist[artists.length()];
            for (int i = 0; i < artists.length(); i++) {
                JSONObject artist = artists.getJSONObject(i);
                result[i] = Artist.builder()
                        .artistName(artist.getString("name"))
                        .type(artist.optString("type", "N/A"))
                        .score(artist.optInt("score", 0))
                        .country(artist.optString("country", "Unknown"))
                        .build();
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching artists: " + e.getMessage());
        }
    }

}
