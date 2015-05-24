package se.tuppload.android.satstrainingapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import se.tuppload.android.satstrainingapp.Adapter.TrainingListAdapter;
import se.tuppload.android.satstrainingapp.Model.ClassType;

import static se.tuppload.android.satstrainingapp.R.layout.class_view;

public class ShowActivityInfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(class_view);
        Bundle extras = getIntent().getExtras();

        TextView className = (TextView) findViewById(R.id.class_name);
        TextView duration = (TextView) findViewById(R.id.class_duration_time);
        TextView center = (TextView) findViewById(R.id.center_name);
        TextView date = (TextView) findViewById(R.id.date);
        TextView instructor = (TextView) findViewById(R.id.class_view_instructor);
        TextView description = (TextView) findViewById(R.id.class_information);

        ProgressBar cardio = (ProgressBar) findViewById(R.id.fitness_bar);

        className.setText(extras.getString("CLASSTYPE"));
        duration.setText(extras.getString("DURATION"));
        center.setText(extras.getString("CENTER"));
        date.setText(extras.getString("DATE"));
        instructor.setText(extras.getString("INSTRUCTOR"));
        description.setText(extras.getString("DESCRIPTION"));

        cardio.setProgress(extras.getInt("CARDIO"));



    }
}
