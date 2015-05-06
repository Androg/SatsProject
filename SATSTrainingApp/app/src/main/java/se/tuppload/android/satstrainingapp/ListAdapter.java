package se.tuppload.android.satstrainingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<UpcomingWorkout> {

    public ListAdapter(Context context, ArrayList<UpcomingWorkout> upcomingWorkouts) {
        super(context,0, upcomingWorkouts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) throws NullPointerException {

        try {
            UpcomingWorkout upcomingWorkout = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter, parent, false);
            }

            TextView workoutType = (TextView) convertView.findViewById(R.id.workout_type);
            TextView gymLocation = (TextView) convertView.findViewById(R.id.gym_location);
            TextView instructorsName = (TextView) convertView.findViewById(R.id.instructors_name);
            TextView waitingListCount = (TextView) convertView.findViewById(R.id.waiting_list_count);
            TextView startTimeHour = (TextView) convertView.findViewById(R.id.activity_starttime_hour);
            TextView startTimeMinutes = (TextView) convertView.findViewById(R.id.activity_starttime_minutes);
            TextView activityDuration = (TextView) convertView.findViewById(R.id.activity_length_time);
            ImageView img = (ImageView) convertView.findViewById(R.id.icon);

            workoutType.setText(upcomingWorkout.mWorkoutType);
            gymLocation.setText(upcomingWorkout.mCenterName);
            instructorsName.setText(upcomingWorkout.mInstructorsName);
            waitingListCount.setText(Integer.toString(upcomingWorkout.mWaitingListCount));
            startTimeHour.setText(upcomingWorkout.mStartTimeHour);
            startTimeMinutes.setText(upcomingWorkout.mStartTimeMinutes);
            activityDuration.setText(upcomingWorkout.mDurationInMinutes + " min");



            if(waitingListCount.getText().toString().equals("0")) {
                waitingListCount.setVisibility(View.GONE);
                img.setVisibility(View.GONE);
            }


        } catch (NullPointerException e) {

        }

        return convertView;
    }

}