package Use_case.event_search;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import data_transfer_object.Event;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * MusicBrainz Event Repository.
 */
public class MusicBrainzEventRepository {

    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String FORMAT = "fmt=json";

    /**
     * Return a list of events.
     * @param eventName event name
     * @param location location
     * @param limit limit
     * @param offset offset
     * @return a list of events
     * @throws RuntimeException when an error appears
     */
    public Event[] getEvents(String eventName, String location, int limit, int offset) {
        final OkHttpClient client = new OkHttpClient();
        final StringBuilder queryBuilder = new StringBuilder(BASE_URL + "/event/?query=");

        if (eventName != null && !eventName.isEmpty()) {
            queryBuilder.append("event:").append(eventName).append(" ");
        }
        if (location != null && !location.isEmpty()) {
            queryBuilder.append("AND location:").append(location).append(" ");
        }

        queryBuilder.append(String.format("&%s&limit=%d&offset=%d", FORMAT, limit, offset));

        try {
            final Request request = new Request.Builder()
                    .url(queryBuilder.toString())
                    .addHeader("User-Agent", "MusicRating/1.0.0 (delfen.gamma@gmail.com)")
                    .build();

            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            final JSONArray events = responseBody.getJSONArray("events");

            final Event[] result = new Event[events.length()];
            for (int i = 0; i < events.length(); i++) {
                final JSONObject event = events.getJSONObject(i);

                // Initialize placeholders for optional fields with default values
                final String unknown = "Unknown";
                String placeName = unknown;
                String placeId = "";
                String artistName = unknown;
                String artistId = "";

                // Check if "relations" array exists and has the expected objects
                final String name = "name";
                final String id = "id";
                if (event.has("relations")) {
                    final JSONArray relations = event.getJSONArray("relations");
                    for (int j = 0; j < relations.length(); j++) {
                        final JSONObject relation = relations.getJSONObject(j);

                        // Check and extract place information if available
                        if (relation.has("place")) {
                            final JSONObject place = relation.optJSONObject("place");
                            if (place != null) {
                                placeName = place.optString(name, unknown);
                                placeId = place.optString(id, "");
                            }
                        }

                        // Check and extract artist information if available
                        if (relation.has("artist")) {
                            final JSONObject artist = relation.optJSONObject("artist");
                            if (artist != null) {
                                artistName = artist.optString(name, unknown);
                                artistId = artist.optString(id, "");
                            }
                        }
                    }
                }

                String begin = unknown;
                String end = unknown;
                // Check and extract begin and end information
                if (event.has("life-span")) {
                    final JSONObject lifespan = event.getJSONObject("life-span");

                    if (lifespan.has("begin")) {
                        begin = lifespan.optString("begin", unknown);
                    }

                    if (lifespan.has("end")) {
                        end = lifespan.optString("end", unknown);
                    }
                }

                // Build the Event object with all extracted data
                result[i] = Event.builder()
                        .id(event.optString(id, UUID.randomUUID().toString()))
                        .name(event.getString(name))
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
        }

        catch (Exception ex) {
            throw new RuntimeException("Error fetching events: " + ex.getMessage());
        }
    }
}
