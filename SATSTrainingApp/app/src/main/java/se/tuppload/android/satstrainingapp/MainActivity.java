package se.tuppload.android.satstrainingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView searchList = (ListView) findViewById(R.id.list_view);
        final UpcomingWorkout[] upcomingWorkouts;

//        try {
//        final String absoluteUrl = "";
//        //Horrible
//        final String[] rawJsonResponse = new String[1];

//            SatsRestClient.get(absoluteUrl, null, new JsonHttpResponseHandler() {
//
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
//                    //Must fix
//                    rawJsonResponse[0] = setRawJson(jsonResponse.toString());
//                }
//
//            });

//            upcomingWorkouts = getSearchResults(rawJsonResponse[0]);
        UpcomingWorkout[] uwo = new UpcomingWorkout[3];
        uwo[0] = new UpcomingWorkout("Rådis", "Sven", "CORE");
        uwo[1] = new UpcomingWorkout("Medis", "SvenneBanan", "CORE");
        uwo[2] = new UpcomingWorkout("ODIS", "CORE", "SVENNIS");

        ListAdapter adapter = new ListAdapter(this, uwo);

        searchList.setAdapter(adapter);


//    private UpcomingWorkout[] getSearchResults(String rawJsonResponse) throws JSONException {
//        final UpcomingWorkout[] tempUpcomingWorkout = new UpcomingWorkout[10];
//
//        try {
//            JSONObject jsonResponse = new JSONObject(rawJsonResponse);
//            JSONArray searchResult = jsonResponse.getJSONArray("tracks");
//
//            for (int i = 0; i < tempUpcomingWorkout.length; i++) {
//                JSONObject workoutObject = searchResult.getJSONObject(i);
//                tempUpcomingWorkout[i] = new UpcomingWorkout(workoutObject.getString("name"),
//                        workoutObject.getJSONArray("artists").getJSONObject(0).getString("name"),
//                        workoutObject.getJSONObject("album").getString("name"));
//            }
//
//        } catch (JSONException e) {
//            Log.e("MESSAGE_ACTIVITY", "Could not download info", e);
//        }
//        return tempUpcomingWorkout;
//    }

        //Set rawJsonResponse to value from within inner class
//    private String setRawJson(String rawJsonResponse) {
//        return rawJsonResponse;
//    }
    }
}
