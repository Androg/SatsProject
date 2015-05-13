package se.tuppload.android.satstrainingapp.storage;

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
        final String classUrl = "https://api.parse.com/1/classes/class?include=classTypeId";
        final String centerRelativeUrl = "https://api2.sats.com/v1.0/se/centers/";

        SatsRestClient.get(classUrl, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                final ArrayList<UpcomingWorkout> upcomingWorkouts = new ArrayList<>();

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject workoutObject = resultArray.getJSONObject(i);
                        JSONObject subType = workoutObject.getJSONObject("classTypeId");
                        JSONObject dateTime = workoutObject.getJSONObject("startTime");
                        Log.d("CenterId", subType.getString("subType"));

                        final String centerId = workoutObject.getString("centerId");
                        final String instructorId = workoutObject.getString("instructorId");
                        final String workoutType = subType.getString("subType");
                        final String durationInMinutes = workoutObject.getString("durationInMinutes");
                        final int waitingListCount = workoutObject.getInt("waitingListCount");
                        final String startTime = dateTime.getString("iso");

                        final String centerAbsoluteUrl = centerRelativeUrl + centerId;

                        SatsRestClient.get(centerAbsoluteUrl, null, new JsonHttpResponseHandler() {

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                                try {
                                    JSONObject centerObject = jsonResponse.getJSONObject("center");
                                    String centerName = centerObject.getString("name");
                                    upcomingWorkouts.add(new UpcomingWorkout(centerName, instructorId, workoutType, durationInMinutes, waitingListCount, startTime));
                                    Collections.sort(upcomingWorkouts);

                                    TrainingListAdapter adapter = new TrainingListAdapter(activity, upcomingWorkouts);
                                    listView.setAdapter(adapter);

                                } catch (JSONException e) {
                                    Log.e("ERROR", "COULD NOT FIND CENTER-NAME");
                                }
                            }
                        });
                    }

                } catch (JSONException e) {
                    Log.e("ERROR", "COULD NOT FIND ANY RESULTS");
                }
            }
        });
    }
}