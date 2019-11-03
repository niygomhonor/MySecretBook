package com.example.noteyourday.networks;

import com.example.noteyourday.DiaryConstants;
import com.example.noteyourday.models.Event;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpDayServices {
    public static void findEvents(String location, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(DiaryConstants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(DiaryConstants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();
//        final String API_KEY_QUERY_PARAMETER = "location";
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", DiaryConstants.YELP_API_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<Event> processResults(Response response) {
        ArrayList<Event> events = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject yelpJSON = new JSONObject(jsonData);
            JSONArray businessesJSON = yelpJSON.getJSONArray("events");
            if (response.isSuccessful()) {
                for (int i = 0; i < businessesJSON.length(); i++) {
                    JSONObject eventJSON = businessesJSON.getJSONObject(i);
                    String name = eventJSON.getString("name");
                   int attendingCount = eventJSON.getInt("attending_count");
                   String category = eventJSON.getString("category");
                  String imageUrl = eventJSON.getString("image_url");
                    double latitude = eventJSON.getDouble("latitude");
                    double longitude = eventJSON.getDouble("longitude");
                    String description=eventJSON.getString("description");
//                 int cost=eventJSON.getInt("cost");
//                    double costMax=eventJSON.getDouble("costMax");
                    String eventSiteUrl=eventJSON.getString("event_site_url");
                    String id=eventJSON.getString("id");
                    Integer interestedCount=eventJSON.getInt("interested_count");
                    Boolean isCanceled=eventJSON.getBoolean("is_canceled");
                    Boolean isFree=eventJSON.getBoolean("is_free");
                    Boolean isOfficial=eventJSON.getBoolean("is_official");
                    String ticketsUrl=eventJSON.getString("tickets_url");
                    String timeEnd=eventJSON.getString("time_end");
                    String timeStart=eventJSON.getString("time_start");
                    String businessId=eventJSON.getString("business_id");
                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = eventJSON.getJSONObject("location").getJSONArray("display_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }
//
                    Event event = new Event(attendingCount, category, description,  eventSiteUrl,id, imageUrl, interestedCount,  isCanceled,  isFree,  isOfficial,  latitude,  longitude,  name,ticketsUrl, timeEnd, timeStart,
                   businessId);
                    events.add(event);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return events;
    }

}
