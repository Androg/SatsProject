package se.tuppload.android.satstrainingapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {

    TextView workoutType;
    TextView gymLocation;
    TextView instructorsName;
    TextView waitingListCount;
    TextView startTimeHour;
    TextView startTimeMinutes;
    TextView activityDuration;
    TextView activityDate;

    ImageView waitingListImg;

    public ViewHolder(View view) {

        workoutType = (TextView) view.findViewById(R.id.workout_type);
        gymLocation = (TextView) view.findViewById(R.id.gym_location);
        instructorsName = (TextView) view.findViewById(R.id.instructors_name);
        waitingListCount = (TextView) view.findViewById(R.id.waiting_list_count);
        startTimeHour = (TextView) view.findViewById(R.id.activity_starttime_hour);
        startTimeMinutes = (TextView) view.findViewById(R.id.activity_starttime_minutes);
        activityDuration = (TextView) view.findViewById(R.id.activity_length_time);
        activityDate = (TextView) view.findViewById(R.id.activity_date);

        waitingListImg = (ImageView) view.findViewById(R.id.queue_icon);
    }
}