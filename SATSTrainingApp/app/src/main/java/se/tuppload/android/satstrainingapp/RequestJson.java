package se.tuppload.android.satstrainingapp;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestJson {

    public static void getJsonData(final ListView searchList, final Activity activity) {

        SatsRestClient.get(null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
                UpcomingWorkout[] upcomingWorkouts = new UpcomingWorkout[2];

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");
                    Log.d("RESULT RESULT", resultArray.toString());

                    for (int i = 0; i < upcomingWorkouts.length; i++) {
                        JSONObject workoutObject = resultArray.getJSONObject(i);
                        JSONObject subType = workoutObject.getJSONObject("classTypeId");
                        Log.d("CenterId", subType.getString("subType"));
                        upcomingWorkouts[i] = new UpcomingWorkout(workoutObject.getString("name"),
                                workoutObject.getString("instructorId"),
                                subType.getString("subType"),
                                workoutObject.getInt("maxPersonsCount"));
//                                workoutObject.getString("instructorId"),
//                                workoutObject.getString("durationInMinutes"),
//                                subType.getString("name"),
//                                workoutObject.getInt("maxPersonCount"));
//                                workoutObject.getInt("maxPersonsCount"),
//                                workoutObject.getString("name"),
//                                workoutObject.getJSONObject("startTime").getString("iso"));
                    }

                } catch(JSONException e) {
                    Log.e("ERROR", "COULD NOT FIND ANY RESULTS");
                }


                ListAdapter adapter = new ListAdapter(activity, upcomingWorkouts);

                searchList.setAdapter(adapter);
            }
        });
    }
}
