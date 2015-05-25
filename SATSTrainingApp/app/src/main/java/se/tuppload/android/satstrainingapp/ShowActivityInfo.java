package se.tuppload.android.satstrainingapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Random;

import se.tuppload.android.satstrainingapp.Adapter.TrainingListAdapter;
import se.tuppload.android.satstrainingapp.Model.ClassType;

import static se.tuppload.android.satstrainingapp.R.layout.class_view;

public class ShowActivityInfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(class_view);
        Bundle extras = getIntent().getExtras();

        int min = 0;
        int max = 5;
        int randNumber = min + new Random().nextInt(max - min + 1);

        TextView className = (TextView) findViewById(R.id.class_name);
        TextView duration = (TextView) findViewById(R.id.class_duration_time);
        TextView center = (TextView) findViewById(R.id.center_name);
        TextView date = (TextView) findViewById(R.id.date);
        TextView instructor = (TextView) findViewById(R.id.class_view_instructor);
        TextView description = (TextView) findViewById(R.id.class_information);

        ProgressBar cardio = (ProgressBar) findViewById(R.id.fitness_bar_cardio);
        ProgressBar strength = (ProgressBar) findViewById(R.id.fitness_bar_strength);
        ProgressBar flexibility = (ProgressBar) findViewById(R.id.fitness_bar_flexibility);
        ProgressBar balance = (ProgressBar) findViewById(R.id.fitness_bar_balance);
        ProgressBar agility = (ProgressBar) findViewById(R.id.fitness_bar_agility);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.sats_rating);

        className.setText(extras.getString("CLASSTYPE"));
        duration.setText(extras.getString("DURATION"));
        center.setText(extras.getString("CENTER"));
        date.setText(extras.getString("DATE"));
        instructor.setText(extras.getString("INSTRUCTOR"));
        description.setText(extras.getString("DESCRIPTION"));

        cardio.setProgress(extras.getInt("CARDIO"));
        strength.setProgress(extras.getInt("STRENGTH"));
        flexibility.setProgress(extras.getInt("FLEXIBILITY"));
        balance.setProgress(extras.getInt("BALANCE"));
        agility.setProgress(extras.getInt("AGILITY"));

        ratingBar.setRating(randNumber);


    }
}
