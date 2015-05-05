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

    public static void getJsonData(String url,final ListView searchList, final Activity activity) {

        SatsRestClient.get(url, null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
                UpcomingWorkout[] upcomingWorkouts = new UpcomingWorkout[5];

                try {
                    JSONArray resultArray = jsonResponse.getJSONArray("results");
                    Log.d("RESULT RESULT", resultArray.toString());

                    for (int i = 0; i < upcomingWorkouts.length; i++) {
                        JSONObject workoutObject = resultArray.getJSONObject(i);
                        upcomingWorkouts[i] = new UpcomingWorkout(workoutObject.getInt("bookedPersonsCount"),
                                workoutObject.getInt("centerId"),
                                workoutObject.getString("durationInMinutes"),
                                workoutObject.getString("instructorId"),
                                workoutObject.getInt("maxPersonsCount"),
                                workoutObject.getString("name"),
                                workoutObject.getJSONObject("startTime").getString("iso"));
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
