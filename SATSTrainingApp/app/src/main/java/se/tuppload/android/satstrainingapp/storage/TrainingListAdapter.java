package se.tuppload.android.satstrainingapp.storage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.tuppload.android.satstrainingapp.holders.ViewHolder;
import se.tuppload.android.satstrainingapp.R;

public class TrainingListAdapter extends BaseAdapter implements StickyListHeadersAdapter
{

    private static ArrayList<UpcomingWorkout> upcomingWorkouts = new ArrayList<>();
    private LayoutInflater inflater;
    private android.app.Activity activity;
    private final int numberOfPositions;
    private Calendar mCalendar = Calendar.getInstance();
    private final String[] swedish_days = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
    private final String[] swedish_months = {"Januari", "Februari", "Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September", "Oktober", "November", "December"};
    private DateTime date;
    private int NUMBER_OF_VIEWS_SERVED_BY_ADAPTER = 3;
    private int previous = 0;
    private int booked = 1;
    private int own = 2;


    public TrainingListAdapter(Activity activity, ArrayList<UpcomingWorkout> upcomingWorkouts)
    {
        inflater = LayoutInflater.from(activity);
        this.upcomingWorkouts = upcomingWorkouts;
        this.activity = activity;
        inflater = activity.getLayoutInflater();
        numberOfPositions = upcomingWorkouts.size();
        Collections.sort(upcomingWorkouts);
    }


    @Override
    public int getCount()
    {
        return upcomingWorkouts.size();
    }

    @Override
    public UpcomingWorkout getItem(int position)
    {
        return upcomingWorkouts.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        View view = convertView;

        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.booking_item, parent, false);

            //Get views from layout
            holder.workoutType = (TextView) view.findViewById(R.id.pass);
            holder.gymLocation = (TextView) view.findViewById(R.id.center);
            holder.instructorsName = (TextView) view.findViewById(R.id.instructor);
            holder.waitingListCount = (TextView) view.findViewById(R.id.number_participants);
            holder.startTimeHour = (TextView) view.findViewById(R.id.hour);
            holder.startTimeMinutes = (TextView) view.findViewById(R.id.minutes);
            holder.activityDuration = (TextView) view.findViewById(R.id.class_time);
            holder.activityDate = (TextView) view.findViewById(R.id.date_header);

            //Get Waiting list image
            holder.waitingListImg = (ImageView) view.findViewById(R.id.img_participants);

            //Set text to views in layout
            holder.workoutType.setText(upcomingWorkouts.get(position).mWorkoutType);
            holder.gymLocation.setText(upcomingWorkouts.get(position).mCenterName);
            holder.instructorsName.setText(upcomingWorkouts.get(position).mInstructorsName);
            holder.waitingListCount.setText(Integer.toString(upcomingWorkouts.get(position).mWaitingListCount));
            holder.startTimeHour.setText(upcomingWorkouts.get(position).mStartTime.substring(11, 13));
            holder.startTimeMinutes.setText(upcomingWorkouts.get(position).mStartTime.substring(14, 16));
//            holder.activityDate.setText(upcomingWorkouts.get(position).mStartTime.substring(0, 10));
            holder.activityDuration.setText(upcomingWorkouts.get(position).mDurationInMinutes + " min");

            //Hide waiting list count img if no one in queue
            if (upcomingWorkouts.get(position).mWaitingListCount == 0) {
                holder.waitingListCount.setVisibility(View.GONE);
                holder.waitingListImg.setVisibility(View.GONE);
            }

        }
        return view;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent)
    {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.date_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.date_header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
//        mCalendar.setTimeInMillis(Long.parseLong(upcomingWorkouts.get(position).mStartTime));
        String headerText = swedish_days[mCalendar.get(Calendar.DAY_OF_WEEK) - 1] + " " + mCalendar.get(Calendar.DAY_OF_MONTH) + " " + swedish_months[mCalendar.get(Calendar.MONTH)];

        holder.text.setText(headerText);
        return convertView;
    }

    public static ArrayList<UpcomingWorkout> getList()
    {
        return upcomingWorkouts;
    }

    @Override
    public long getHeaderId(int i)
    {
        return i;
    }

    private class HeaderViewHolder
    {
        TextView text;
    }
}