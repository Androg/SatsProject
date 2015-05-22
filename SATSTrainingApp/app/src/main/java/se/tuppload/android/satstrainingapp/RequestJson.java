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
    private final static String CLASSTYPE_TAG = "classTypes";

    private static HashMap<String, ClassType> classTypes = new HashMap<>();

    public static void getJsonData(final StickyListHeadersListView listView, final MainActivity activity) {

        SatsRestClient.getClassTypes(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    getClassTypes(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(CLASSTYPE_TAG, "Could not get classTypes");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d(CLASSTYPE_TAG, "Could not get classTypes");
            }
        });

        SatsRestClient.get(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                final ArrayList<Activity> activities = new ArrayList<>();
                final HashMap<String, Center> centers = new HashMap<>();

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject activityJson = resultArray.getJSONObject(i);

                        if (activityJson.has("bookingId")) {
                            final String centerId = activityJson.getJSONObject("bookingId").getString("center");
                            if (!centers.containsKey(centerId)) {
                                SatsRestClient.getCenter(centerId, new JsonHttpResponseHandler() {

                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                                        try {
                                            centers.put(centerId, getCenter(jsonResponse));

                                        } catch (JSONException e) {
                                            Log.e("ERROR", "COULD NOT FIND CENTER-NAME");
                                        }
                                    }
                                });
                            }
                        }
                        activities.add(getActivity(activityJson));
                        Collections.sort(activities);


                        TrainingListAdapter adapter = new TrainingListAdapter(activity, activities, centers);
                        listView.setAdapter(adapter);

                    }

                } catch (JSONException e) {
                    Log.e("ERROR", "COULD NOT FIND ANY RESULTS");
                }
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
        final se.tuppload.android.satstrainingapp.Model.Class aClass = getClass(classJson);

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

    public static Center getCenter(JSONObject centerJson) throws JSONException {

        JSONObject centerObject = centerJson.getJSONObject("center");
        final boolean availableForOnlineBooking = centerObject.getBoolean("availableForOnlineBooking");
        final boolean isElixia = centerObject.getBoolean("isElixia");
        final String description = centerObject.getString("description");
        final String name = centerObject.getString("name");
        final String url = centerObject.getString("url");
        final String filterId = centerObject.getString("filterId");
        final String centerId = centerObject.getString("id");
        final String latitude = centerObject.getString("lat");
        final String longitude = centerObject.getString("long");
        final String regionId = centerObject.getString("regionId");

        return new Center(availableForOnlineBooking, isElixia, description,
                name, url, filterId, centerId, latitude, longitude, regionId);
    }

    public static void getClassTypes(JSONObject classTypeJsonResult) throws JSONException {

        JSONArray classTypeJsonJSONArray = classTypeJsonResult.getJSONArray("classTypes");

        for (int i = 0; i < classTypeJsonJSONArray.length(); i++) {
            JSONObject classTypeJson = classTypeJsonJSONArray.getJSONObject(i);

            final String classCategoryIdsString = classTypeJson.getString("classCategories");
            final List<String> classCategoryIds = Arrays.asList(classCategoryIdsString.split("\\s*,\\s*"));
            final String description = classTypeJson.getString("description");
            final String id = classTypeJson.getString("id");
            final String name = classTypeJson.getString("name");
            final String videoUrl = classTypeJson.getString("videoUrl");
            final JSONArray profileJsonArray = classTypeJson.getJSONArray("profile");

            final HashMap<String, Profile> profiles = getProfile(profileJsonArray);

            ClassType classType = new ClassType(classCategoryIds, description, id, name, profiles, videoUrl);
            classTypes.put(classType.id, classType);

        }

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