package data_access;

import Use_case.event_search.EventSearchDataAccessInterface;
import data_transfer_object.Event;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.UUID;

public class MusicBrainzEventRepository implements EventSearchDataAccessInterface {

    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
    private static final String FORMAT = "fmt=json";

    @Override
    public Event[] getEvents(String eventName, String location, int limit, int offset) {
        OkHttpClient client = new OkHttpClient();
        StringBuilder queryBuilder = new StringBuilder(BASE_URL + "/event/?query=");

        if (eventName != null && !eventName.isEmpty()) {
            queryBuilder.append("event:").append(eventName).append(" ");
        }
        if (location != null && !location.isEmpty()) {
            queryBuilder.append("AND area:").append(location).append(" ");
        }

        queryBuilder.append(String.format("&%s&limit=%d&offset=%d", FORMAT, limit, offset));

        try {
            System.out.println("API Request URL: " + queryBuilder.toString());

            Request request = new Request.Builder()
                    .url(queryBuilder.toString())
                    .addHeader("User-Agent", "MusicRating/1.0.0 (your-email@example.com)")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBodyString = response.body().string();
            System.out.println("API Response Body: " + responseBodyString);

            JSONObject responseBody = new JSONObject(responseBodyString);

            JSONArray eventsArray = responseBody.getJSONArray("events");

            Event[] result = new Event[eventsArray.length()];
            for (int i = 0; i < eventsArray.length(); i++) {
                JSONObject eventJson = eventsArray.getJSONObject(i);

                String id = eventJson.optString("id", UUID.randomUUID().toString());
                String name = eventJson.optString("name", "Unknown Event");
                String type = eventJson.optString("type", "Unknown Type");
                String beginDate = eventJson.optString("life-span.begin", "Unknown Begin Date");
                String endDate = eventJson.optString("life-span.end", "Unknown End Date");
                String time = eventJson.optString("time", "Unknown Time");
                String placeName = "Unknown Place";
                String placeId = UUID.randomUUID().toString();
                String artistName = "Unknown Artist";
                String artistId = UUID.randomUUID().toString();
                int score = eventJson.optInt("score", 0);

                // Get place information if available
                if (eventJson.has("place")) {
                    JSONObject placeJson = eventJson.getJSONObject("place");
                    placeName = placeJson.optString("name", placeName);
                    placeId = placeJson.optString("id", placeId);
                }

                // Get artist information if available
                if (eventJson.has("relations")) {
                    JSONArray relations = eventJson.getJSONArray("relations");
                    for (int j = 0; j < relations.length(); j++) {
                        JSONObject relation = relations.getJSONObject(j);
                        if ("artist".equals(relation.optString("type"))) {
                            JSONObject artist = relation.getJSONObject("artist");
                            artistName = artist.optString("name", artistName);
                            artistId = artist.optString("id", artistId);
                            break;
                        }
                    }
                }

                result[i] = Event.builder()
                        .id(id)
                        .name(name)
                        .type(type)
                        .beginDate(beginDate)
                        .endDate(endDate)
                        .time(time)
                        .placeName(placeName)
                        .placeId(placeId)
                        .artistName(artistName)
                        .artistId(artistId)
                        .score(score)
                        .build();
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching events: " + e.getMessage());
        }
    }
}
