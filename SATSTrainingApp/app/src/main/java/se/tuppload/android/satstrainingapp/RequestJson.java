//package se.tuppload.android.satstrainingapp;
//
//import android.app.Activity;
//import android.util.Log;
//import android.widget.ListView;
//
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.JsonHttpResponseHandler;
//
//import org.apache.http.Header;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class RequestJson {
//
//    public static void getJsonData(final ListView searchList, final Activity activity) {
//
//        SatsRestClient.get(null, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
//
//                ArrayList<UpcomingWorkout> upcomingWorkouts = new ArrayList<>();
//
//                try {
//                    JSONArray resultArray = jsonResponse.getJSONArray("results");
//                    Log.d("RESULT RESULT", resultArray.toString());
//                    Log.d("resultArray.length", "" + resultArray.length());
//
//                    for (int i = 0; i < resultArray.length(); i++) {
//                        JSONObject workoutObject = resultArray.getJSONObject(i);
//                        JSONObject subType = workoutObject.getJSONObject("classTypeId");
//                        JSONObject startTime = workoutObject.getJSONObject("startTime");
//                        Log.d("CenterId", subType.getString("subType"));
//                        upcomingWorkouts.add(new UpcomingWorkout(workoutObject.getString("centerId"),
//                                workoutObject.getString("instructorId"),
//                                subType.getString("subType"),
//                                workoutObject.getString("durationInMinutes"),
//                                workoutObject.getInt("waitingListCount"),
//                                startTime.getString("iso").substring(11,13),
//                                startTime.getString("iso").substring(14,16)));
//                    }
//
//                } catch (JSONException e) {
//                    Log.e("ERROR", "COULD NOT FIND ANY RESULTS");
//                }
//
//                ListAdapter adapter = new ListAdapter(activity, upcomingWorkouts);
//
//                searchList.setAdapter(adapter);
//            }
//        });
//    }
//
//
//}



package se.tuppload.android.satstrainingapp;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class RequestJson {

    public static void getJsonData(final ListView searchList, final Activity activity) {
        final String classUrl = "https://api.parse.com/1/classes/class?include=classTypeId";
        final String centerRelativeUrl = "https://api2.sats.com/v1.0/se/centers/";

        SatsRestClient.get(classUrl ,null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                final ArrayList<UpcomingWorkout> upcomingWorkouts = new ArrayList<UpcomingWorkout>();

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject workoutObject = resultArray.getJSONObject(i);
                        JSONObject subType = workoutObject.getJSONObject("classTypeId");
                        JSONObject startTime = workoutObject.getJSONObject("startTime");
                        Log.d("CenterId", subType.getString("subType"));
                        final boolean lastRequest = (i == resultArray.length() - 1);

                        final String centerId = workoutObject.getString("centerId");
                        final String instructorId = workoutObject.getString("instructorId");
                        final String workoutType = subType.getString("subType");
                        final String durationInMinutes = workoutObject.getString("durationInMinutes");
                        final int waitingListCount = workoutObject.getInt("waitingListCount");
                        final String startTimeHours = startTime.getString("iso").substring(11, 13);
                        final String startTimeMinutes = startTime.getString("iso").substring(14, 16);


                        final String centerAbsoluteUrl = centerRelativeUrl + centerId;

                        SatsRestClient.get(centerAbsoluteUrl, null, new JsonHttpResponseHandler() {

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                                try {
                                    JSONObject centerObject = jsonResponse.getJSONObject("center");
                                    String centerName = centerObject.getString("name");
                                    upcomingWorkouts.add(new UpcomingWorkout(centerName, instructorId, workoutType, durationInMinutes, waitingListCount, startTimeHours, startTimeMinutes));

                                    if (lastRequest) {
                                        ListAdapter adapter = new ListAdapter(activity, upcomingWorkouts);
                                        searchList.setAdapter(adapter);
                                    }

                                } catch (JSONException e) {
                                    Log.e("ERROR", "COULD NOT FIND CENTER-NAME");
                                }
                            }
                        });
                    }

                } catch(JSONException e) {
                    Log.e("ERROR", "COULD NOT FIND ANY RESULTS");
                }
            }
        });
    }
}