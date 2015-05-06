package se.tuppload.android.satstrainingapp;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RequestJson {

    public static void getJsonData(final ListView searchList, final Activity activity) {

        SatsRestClient.get(null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {

                ArrayList<UpcomingWorkout> upcomingWorkouts = new ArrayList<>();

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");
                    Log.d("RESULT RESULT", resultArray.toString());
                    Log.d("resultArray.length", "" + resultArray.length());

                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject workoutObject = resultArray.getJSONObject(i);
                        JSONObject subType = workoutObject.getJSONObject("classTypeId");
                        JSONObject startTime = workoutObject.getJSONObject("startTime");
                        Log.d("CenterId", subType.getString("subType"));
                        upcomingWorkouts.add(new UpcomingWorkout(workoutObject.getString("centerId"),
                                workoutObject.getString("instructorId"),
                                subType.getString("subType"),
                                workoutObject.getString("durationInMinutes"),
                                workoutObject.getInt("waitingListCount"),
                                startTime.getString("iso").substring(11, 12),
                                startTime.getString("iso").substring(15, 16)));

                    }

                } catch (JSONException e) {
                    Log.e("ERROR", "COULD NOT FIND ANY RESULTS");
                }

                ListAdapter adapter = new ListAdapter(activity, upcomingWorkouts);

                searchList.setAdapter(adapter);
            }
        });
    }


}
