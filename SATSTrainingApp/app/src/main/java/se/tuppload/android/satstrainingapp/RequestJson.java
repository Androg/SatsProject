package se.tuppload.android.satstrainingapp;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class RequestJson {

    public static void getJsonData(final StickyListHeadersListView listView, final MainActivity activity) {
        final String classUrl = "https://api.parse.com/1/classes/activities?include=bookingId.class,subType";
        final String centerRelativeUrl = "https://api2.sats.com/v1.0/se/centers/";

        SatsRestClient.get(classUrl, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                final ArrayList<Activity> activities = new ArrayList<>();

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject activityJson = resultArray.getJSONObject(i);
                        Log.d("resultlength ", "" + resultArray.length());


//                            final String centerAbsoluteUrl = centerRelativeUrl + centerId;
//                            SatsRestClient.get(centerAbsoluteUrl, null, new JsonHttpResponseHandler() {
//
//                                @Override
//                                public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
//
//                                    try {
//                                        JSONObject centerObject = jsonResponse.getJSONObject("center");
//                                        String centerName = centerObject.getString("name");
//                                        final Booking booking = new Booking(status, aClass, centerName, bookingId, positionInQueue);
//                                        activities.add(new Activity(booking, instructorId, wor, durationInMinutes, waitingListCount, startTime));
//                                        activities.add(new Activity(centerName, instructorId, workoutType, durationInMinutes, waitingListCount, startTime));
//                                        Collections.sort(activities);
//
//                                        TrainingListAdapter adapter = new TrainingListAdapter(activity, activities);
//                                        listView.setAdapter(adapter);
//
//                                    } catch (JSONException e) {
//                                        Log.e("ERROR", "COULD NOT FIND CENTER-NAME");
//                                    }
//                                }
//                            });

                        activities.add(getActivity(activityJson));
                        Collections.sort(activities);

                        TrainingListAdapter adapter = new TrainingListAdapter(activity, activities);
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
        if (activityJson.has("bookingId")){
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

    public static Booking getBooking(JSONObject activityJson) throws JSONException {


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
//    public static void makeActivity(Booking booking, final StickyListHeadersListView listView ,final MainActivity activity){
//
//        ArrayList<Activity> activities = new ArrayList<>();
//        activities.add(new Activity(booking, ))
//        TrainingListAdapter adapter = new TrainingListAdapter(activity, activities);
//        listView.setAdapter(adapter);
//    }

}