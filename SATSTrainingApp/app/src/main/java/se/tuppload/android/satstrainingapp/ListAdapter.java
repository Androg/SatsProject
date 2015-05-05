package se.tuppload.android.satstrainingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<UpcomingWorkout> {

    public ListAdapter(Context context, UpcomingWorkout[] upcomingWorkouts) {
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
            TextView maxPersonCount = (TextView) convertView.findViewById(R.id.max_person_count);
//            TextView time = (TextView) convertView.findViewById(R.id.time);


            workoutType.setText(upcomingWorkout.mWorkoutType);
            gymLocation.setText(upcomingWorkout.mGymLocation);
            instructorsName.setText(upcomingWorkout.mInstructorsName);
            maxPersonCount.setText(Integer.toString(upcomingWorkout.mMaxPersonCount));



        } catch (NullPointerException e) {

        }

        return convertView;
    }

}