package se.tuppload.android.satstrainingapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class TrainingListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ArrayList<UpcomingWorkout> upcomingWorkouts = new ArrayList<>();
    private LayoutInflater inflater;

    public TrainingListAdapter(Context context, ArrayList<UpcomingWorkout> upcomingWorkouts) {
        inflater = LayoutInflater.from(context);
        this.upcomingWorkouts = upcomingWorkouts;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        View view = convertView;
        if (view == null) {
            holder = new HeaderViewHolder();
            view = inflater.inflate(R.layout.sticky_header, parent, false);
            holder.testing = (TextView) view.findViewById(R.id.workout_tense);
            view.setTag(holder);

//          String headerText = upcomingWorkouts.get(position).mStartTime;
//          holder.testing.setText(headerText);
        }
        return view;
    }

    @Override
    public long getHeaderId(int position) {
        return upcomingWorkouts.get(position).mInstructorsName.subSequence(0, 1).charAt(0);
    }

    @Override
    public int getCount() {
        return upcomingWorkouts.size();
    }

    @Override
    public UpcomingWorkout getItem(int position) {
        return upcomingWorkouts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_adapter, parent, false);

            //Get views from layout
            holder.workoutType = (TextView) view.findViewById(R.id.workout_type);
            holder.gymLocation = (TextView) view.findViewById(R.id.gym_location);
            holder.instructorsName = (TextView) view.findViewById(R.id.instructors_name);
            holder.waitingListCount = (TextView) view.findViewById(R.id.waiting_list_count);
            holder.startTimeHour = (TextView) view.findViewById(R.id.activity_starttime_hour);
            holder.startTimeMinutes = (TextView) view.findViewById(R.id.activity_starttime_minutes);
            holder.activityDuration = (TextView) view.findViewById(R.id.activity_length_time);
            holder.activityDate = (TextView) view.findViewById(R.id.activity_date);

            //Get Waiting list image
            holder.waitingListImg = (ImageView) view.findViewById(R.id.queue_icon);

            //Set text to views in layout
            holder.workoutType.setText(upcomingWorkouts.get(position).mWorkoutType);
            holder.gymLocation.setText(upcomingWorkouts.get(position).mCenterName);
            holder.instructorsName.setText(upcomingWorkouts.get(position).mInstructorsName);
            holder.waitingListCount.setText(Integer.toString(upcomingWorkouts.get(position).mWaitingListCount));
            holder.startTimeHour.setText(upcomingWorkouts.get(position).mStartTime.substring(11, 13));
            holder.startTimeMinutes.setText(upcomingWorkouts.get(position).mStartTime.substring(14, 16));
            holder.activityDate.setText(upcomingWorkouts.get(position).mStartTime.substring(0, 10));
            holder.activityDuration.setText(upcomingWorkouts.get(position).mDurationInMinutes + " min");

            //Hide waiting list count img if no one in queue
            if(upcomingWorkouts.get(position).mWaitingListCount == 0) {
                holder.waitingListCount.setVisibility(View.GONE);
                holder.waitingListImg.setVisibility(View.GONE);
            }

        }
        return view;
    }
}
