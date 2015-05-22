package se.tuppload.android.satstrainingapp;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import se.tuppload.android.satstrainingapp.Model.*;
import se.tuppload.android.satstrainingapp.Model.Class;

public class RequestJson {

    private static final String CLASSTYPES = "classTypes";
    private static final String CENTER = "centers";
    public static ArrayList<Activity> activities = new ArrayList<>();
    public HashMap<String, Center> centers = new HashMap<>();
    public static HashMap<String, ClassType> classTypes = new HashMap<>();

    public static void getJsonData(final StickyListHeadersListView listView, final MainActivity activity) {

        SatsRestClient.get(CLASSTYPES, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    classTypes = getClassTypes(response);
                    Log.d("ClassTypes length", "" + classTypes.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(CLASSTYPES, "Could not get classTypes");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d(CLASSTYPES, "Could not get classTypes");
            }
        });

        SatsRestClient.get(CENTER, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {


                try {
                    getCenter(jsonResponse);
                } catch (JSONException e) {
                    Log.e(CENTER, "COULD NOT FIND CENTER-NAME");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d(CENTER, "Could not get centers");
            }
        });

        SatsRestClient.get(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                final HashMap<String, Center> centers = new HashMap<>();

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject activityJson = resultArray.getJSONObject(i);

                        activities.add(getActivity(activityJson));
                        Collections.sort(activities);

                        TrainingListAdapter adapter = new TrainingListAdapter(activity, activities, centers);
                        listView.setAdapter(adapter);

                    }

                } catch (JSONException e) {
                    Log.e("ERROR", "COULD NOT FIND ANY RESULTS");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d("ACTIVITIES", "Could not get activities");
            }
        });
    }

    public static Activity getActivity(JSONObject activityJson) throws JSONException {

        Booking booking = null;
        if (activityJson.has("bookingId")) {
            booking = getBooking(activityJson);
        }
        final String comment = activityJson.getString("comment");
        final String date = activityJson.getJSONObject("date").getString("iso");
        final int distanceInKm = activityJson.getInt("distanceInKm");
        final int durationInMinutes = activityJson.getInt("durationInMinutes");
        final String id = activityJson.getString("objectId");
        final String source = activityJson.getString("source");
        final String status = activityJson.getString("status");
        final String subType = activityJson.getJSONObject("subType").getString("subType");
        final String type = activityJson.getString("type");

        return new Activity(booking, comment, date, distanceInKm, durationInMinutes, id, source, status, subType, type);
    }

    public static Booking getBooking(final JSONObject activityJson) throws JSONException {

        final JSONObject bookingJson = activityJson.getJSONObject("bookingId");
        final String status = bookingJson.getString("status");
        final String center = bookingJson.getString("center");
        final String bookingId = bookingJson.getString("objectId");
        final int positionInQueue = bookingJson.getInt("positionInQueue");
        final JSONObject classJson = bookingJson.getJSONObject("class");
        final Class aClass = getClass(classJson);

        return new Booking(status, aClass, center, bookingId, positionInQueue);
    }

    public static Class getClass(JSONObject classJson) throws JSONException {

        final String centerId = classJson.getString("centerId");
        final String classTypeId = classJson.getString("classTypeId");
        final int durationInMinutes = classJson.getInt("durationInMinutes");
        final String id = classJson.getString("objectId");
        final String instructorId = classJson.getString("instructorId");
        final String name = classJson.getString("name");
        final String startTime = classJson.getJSONObject("startTime").getString("iso");
        final int bookedPersonsCount = classJson.getInt("bookedPersonsCount");
        final int maxPersonsCount = classJson.getInt("maxPersonsCount");
        final int waitingListCount = classJson.getInt("waitingListCount");

        return new Class(centerId, classTypeId, durationInMinutes, id, instructorId, name, startTime,
                bookedPersonsCount, maxPersonsCount, waitingListCount);
    }

    public static void getCenter(JSONObject centerJson) throws JSONException {
        final HashMap<String, Center> centers = new HashMap<>();

        JSONArray centerRegionsJson = centerJson.getJSONArray("regions");
        Log.d("REGIONJSONARRAY", "------------------"+centerRegionsJson.length());

        for (int i = 0; i < centerRegionsJson.length(); i++) {
            JSONObject regionsJson = centerRegionsJson.getJSONObject(i);

            final JSONArray centersJsonArray = regionsJson.getJSONArray("centers");
            Log.d("CENTERJSONARRAY", "-------------"+centersJsonArray.length());
            for (int j = 0; j < centersJsonArray.length(); j++) {
                JSONObject centerJsonObject = centersJsonArray.getJSONObject(j);


//        JSONObject centerJsonObject = centerJson.getJSONObject("center");
                final boolean availableForOnlineBooking = centerJsonObject.getBoolean("availableForOnlineBooking");
                final boolean isElixia = centerJsonObject.getBoolean("isElixia");
                final String description = centerJsonObject.getString("description");
                final String name = centerJsonObject.getString("name");
                final String url = centerJsonObject.getString("videoUrl");
                final String filterId = centerJsonObject.getString("filterId");
                final String centerId = centerJsonObject.getString("id");
                final Long latitude = centerJsonObject.getLong("lat");
                final Long longitude = centerJsonObject.getLong("long");
                final String regionId = centerJsonObject.getString("regionId");

                Center center = new Center(availableForOnlineBooking, isElixia, description,
                        name, url, filterId, centerId, latitude, longitude, regionId);
                centers.put(center.centerId, center);
            }
        }
        Log.d("CENTERMAP-SIZE-------", ""+centers.size());
    }

    public static HashMap<String, ClassType> getClassTypes(JSONObject classTypeJsonResult) throws JSONException {
//        final HashMap<String, ClassType> classTypes = new HashMap<>();

        JSONArray classTypeJsonJSONArray = classTypeJsonResult.getJSONArray("classTypes");

        for (int i = 0; i < classTypeJsonJSONArray.length(); i++) {
            JSONObject classTypeJson = classTypeJsonJSONArray.getJSONObject(i);


            final String classCategoryIdsString = classTypeJson.getString("classCategories");
            final List<String> classCategoryIds = Arrays.asList(classCategoryIdsString.split("\\s*,\\s*"));
            final String description = classTypeJson.getString("description");
            final String id = classTypeJson.getString("id");
            final String name = classTypeJson.getString("name");
            final String videoUrl = classTypeJson.getString("videoUrl");
//            final JSONArray profileJsonArray = classTypeJson.getJSONArray("profle");
//            final HashMap<String, Profile> profiles = getProfile(profileJsonArray);

            ClassType classType = new ClassType(classCategoryIds, description, id, name, null, videoUrl);
            classTypes.put(classType.id, classType);

        }
        return classTypes;
    }

    public static HashMap<String, Profile> getProfile(JSONArray profileJsonArray) throws JSONException {
        final HashMap<String, Profile> profiles = new HashMap<>();

        for (int i = 0; i < profileJsonArray.length(); i++) {
            final JSONObject profileJson = profileJsonArray.getJSONObject(i);

            final String id = profileJson.getString("id");
            final String name = profileJson.getString("name");
            final int value = profileJson.getInt("value");

            Profile profile = new Profile(id, name, value);
            profiles.put(profile.id, profile);

        }
        return profiles;

    }
}