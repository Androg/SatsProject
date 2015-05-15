package se.tuppload.android.satstrainingapp;

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
import se.tuppload.android.satstrainingapp.Holders.ViewHolder;

public class TrainingListAdapter extends BaseAdapter implements StickyListHeadersAdapter
{

    private static ArrayList<Activity> activities = new ArrayList<>();
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


    public TrainingListAdapter(android.app.Activity activity, ArrayList<Activity> activities)
    {
        inflater = LayoutInflater.from(activity);
        this.activities = activities;
        this.activity = activity;
        inflater = activity.getLayoutInflater();
        numberOfPositions = activities.size();
        Collections.sort(activities);
    }


    @Override
    public int getCount()
    {
        return activities.size();
    }

    @Override
    public Activity getItem(int position)
    {
        return activities.get(position);
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
            view = inflater.inflate(R.layout.activity_adapter, parent, false);

            //Get views from layout
            holder.workoutType = (TextView) view.findViewById(R.id.pass);
            holder.gymLocation = (TextView) view.findViewById(R.id.center);
            holder.instructorsName = (TextView) view.findViewById(R.id.instructor);
            holder.positionInQueue = (TextView) view.findViewById(R.id.number_participants);
            holder.startTimeHour = (TextView) view.findViewById(R.id.hour);
            holder.startTimeMinutes = (TextView) view.findViewById(R.id.minutes);
            holder.activityDuration = (TextView) view.findViewById(R.id.class_time);
            holder.activityDate = (TextView) view.findViewById(R.id.date_header);

            //Get Waiting list image
            holder.positionInQueueImg = (ImageView) view.findViewById(R.id.img_participants);

            //Set text to views in layout
            holder.workoutType.setText(activities.get(position).subType);
//            holder.gymLocation.setText(activities.get(position).booking.center);
            holder.gymLocation.setText("CenterName TODO");
//            holder.instructorsName.setText(activities.get(position).booking.aClass.instructorId);
            holder.instructorsName.setText("instructorName TODO");
//            holder.positionInQueue.setText(Integer.toString(activities.get(position).booking.positionInQueue));
            holder.positionInQueue.setText("PosInQueue TODO");
            holder.startTimeHour.setText(activities.get(position).date.substring(11, 13));
            holder.startTimeMinutes.setText(activities.get(position).date.substring(14, 16));
//            holder.activityDate.setText(activities.get(position).startTime.substring(0, 10));
            holder.activityDuration.setText(activities.get(position).durationInMinutes + " min");

            //Hide waiting list count img if no one in queue
//            if (activities.get(position).booking.status.equals("QUEUED")) {
//                holder.positionInQueue.setVisibility(View.GONE);
//                holder.positionInQueueImg.setVisibility(View.GONE);
//            }
        if (activities.get(position).distanceInKm == 0) { // TODO
                holder.positionInQueue.setVisibility(View.GONE);
                holder.positionInQueueImg.setVisibility(View.GONE);
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
//        mCalendar.setTimeInMillis(Long.parseLong(Activity.get(position)startTime));
        String headerText = swedish_days[mCalendar.get(Calendar.DAY_OF_WEEK) - 1] + " " + mCalendar.get(Calendar.DAY_OF_MONTH) + " " + swedish_months[mCalendar.get(Calendar.MONTH)];

        holder.text.setText(headerText);
        return convertView;
    }

    public static ArrayList<Activity> getList()
    {
        return activities;
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