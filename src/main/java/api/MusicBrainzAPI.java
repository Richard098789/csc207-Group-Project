package api;

import data_transfer_object.Recording;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MusicBrainzAPI {
    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String FORMAT = "fmt=json";

    public Recording[] getSongsByArtist(String artistMBID) {
        OkHttpClient client = new OkHttpClient();
        String queryUrl = String.format("%s/recording?artist=%s&limit=10&%s", BASE_URL, artistMBID, FORMAT);

        try {
            Request request = new Request.Builder()
                    .url(queryUrl)
                    .addHeader("User-Agent", "MusicRating/1.0.0 (delfen.gamma@gmail.com)")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());

            JSONArray recordings = responseBody.getJSONArray("recordings");
            Recording[] result = new Recording[recordings.length()];

            for (int i = 0; i < recordings.length(); i++) {
                JSONObject recording = recordings.getJSONObject(i);

                // Extract the recording details
                String title = recording.getString("title");
                String id = recording.getString("id");
                int length = recording.optInt("length", 0);

                // Assuming Recording is an entity with a builder pattern
                result[i] = Recording.builder()
                        .id(id)
                        .title(title)
                        .length(length)
                        .build();
            }

            return result;

        } catch (IOException | org.json.JSONException e) {
            throw new RuntimeException("Error fetching songs: " + e.getMessage());
        }
    }
}
