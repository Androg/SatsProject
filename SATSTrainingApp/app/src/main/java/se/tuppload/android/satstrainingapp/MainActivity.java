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

        UpcomingWorkout[] uwo = new UpcomingWorkout[3];
        uwo[0] = new UpcomingWorkout("Rådis", "Sven", "CORE");
        uwo[1] = new UpcomingWorkout("Medis", "SvenneBanan", "CORE");
        uwo[2] = new UpcomingWorkout("ODIS", "CORE", "SVENNIS");

        ListAdapter adapter = new ListAdapter(this, uwo);

        searchList.setAdapter(adapter);

    }
}
