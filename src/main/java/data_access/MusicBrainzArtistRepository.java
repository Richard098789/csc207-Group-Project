package data_access;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import Use_case.artist_search.ArtistSearchDataAccessInterface;
import Use_case.read_from_db.ReadSongDataAccessInterface;
import data_transfer_object.Artist;
import data_transfer_object.Recording;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * The data access object of musicBrainz artist repository.
 */
public class MusicBrainzArtistRepository implements ArtistSearchDataAccessInterface,
        ReadSongDataAccessInterface {

    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String FORMAT = "fmt=json";

    /**
     * Return the list of artists.
     * @param artistName artist name
     * @param country country of interest
     * @param limit limit
     * @param offset offset
     * @return the list of artists.
     * @throws RuntimeException if there is error fetching artists.
     */
    public Artist[] getArtists(String artistName, String country, int limit, int offset) {
        final OkHttpClient client = new OkHttpClient();
        final StringBuilder queryBuilder = new StringBuilder(BASE_URL + "/artist/?query=");

        if (artistName != null && !artistName.isEmpty()) {
            queryBuilder.append("artist:").append(artistName).append(" ");
        }
        if (country != null && !country.isEmpty()) {
            queryBuilder.append("AND country:").append(country).append(" ");
        }

        queryBuilder.append(String.format("&%s&limit=%d&offset=%d", FORMAT, limit, offset));

        try {
            final Request request = new Request.Builder()
                    .url(queryBuilder.toString())
                    .addHeader("User-Agent", "MusicRating/1.0.0 (delfen.gamma@gmail.com)")
                    .build();

            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            final JSONArray artists = responseBody.getJSONArray("artists");

            final Artist[] result = new Artist[artists.length()];
            for (int i = 0; i < artists.length(); i++) {
                final JSONObject artist = artists.getJSONObject(i);

                // Extract artist ID from the response or generate one if not available
                final String id = artist.optString("id", UUID.randomUUID().toString());

                result[i] = Artist.builder()
                        // Use the extracted or generated ID
                        .id(id)
                        .artistName(artist.getString("name"))
                        .type(artist.optString("type", "N/A"))
                        .score(artist.optInt("score", 0))
                        .country(artist.optString("country", "Unknown"))
                        .build();
            }

            return result;
        }

        catch (Exception ex) {
            throw new RuntimeException("Error fetching artists: " + ex.getMessage());
        }
    }

    @Override
    public Recording[] readTopSongs(String artistID) {
        final OkHttpClient client = new OkHttpClient();
        final String queryUrl = String.format("%s/recording?artist=%s&limit=10&%s", BASE_URL, artistID, FORMAT);

        try {
            final Request request = new Request.Builder()
                    .url(queryUrl)
                    .addHeader("User-Agent", "MusicRating/1.0.0 (delfen.gamma@gmail.com)")
                    .build();

            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            final JSONArray recordings = responseBody.getJSONArray("recordings");
            final Recording[] result = new Recording[recordings.length()];

            for (int i = 0; i < recordings.length(); i++) {
                final JSONObject recording = recordings.getJSONObject(i);

                // Extract the recording details
                final String title = recording.getString("title");
                final String id = recording.getString("id");
                final int length = recording.optInt("length", 0);

                // Assuming Recording is an entity with a builder pattern
                result[i] = Recording.builder()
                        .id(id)
                        .title(title)
                        .length(length)
                        .build();
            }

            return result;

        }

        catch (IOException | org.json.JSONException ex) {
            throw new RuntimeException("Error fetching songs: " + ex.getMessage());
        }
    }
}
