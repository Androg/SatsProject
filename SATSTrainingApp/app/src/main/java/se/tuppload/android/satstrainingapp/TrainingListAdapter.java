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
import se.tuppload.android.satstrainingapp.Holders.OwnViewHolder;
import se.tuppload.android.satstrainingapp.Holders.PreviousViewHolder;
import se.tuppload.android.satstrainingapp.Holders.ViewHolder;

public class TrainingListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private static ArrayList<Activity> activities = new ArrayList<>();
    private LayoutInflater inflater;
    private android.app.Activity activity;
    private final int numberOfPositions;
    private Calendar mCalendar = Calendar.getInstance();
    private final String[] swedish_days = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
    private final String[] swedish_months = {"Januari", "Februari", "Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September", "Oktober", "November", "December"};
    private DateTime date;
    private DateTime activityDate;
    private static final int VIEWTYPE_COUNT = 3;
    private static final int PREVIOUS = 0;
    private static final int BOOKED = 1;
    private static final int OWN = 2;


    public TrainingListAdapter(android.app.Activity activity, ArrayList<Activity> activities) {
        inflater = LayoutInflater.from(activity);
        this.activities = activities;
        this.activity = activity;
        inflater = activity.getLayoutInflater();
        numberOfPositions = activities.size();
        Collections.sort(activities);

    }

    @Override
    public int getViewTypeCount() {
        return VIEWTYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {

        activityDate = DateTime.parse(getItem(position).date);
        if (getItem(position).status.equals("COMPLETE") || activityDate.isBeforeNow()) {
            return PREVIOUS;
        } else {
            if (getItem(position).type.equals("GROUP")) {
                return BOOKED;
            } else {
                return OWN;
            }
        }
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public Activity getItem(int position) {
        return activities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        OwnViewHolder ownHolder;
        PreviousViewHolder previousHolder;
        ViewHolder bookedHolder;

        int type = getItemViewType(position);
        switch (type) {
            case PREVIOUS:
                if (view == null) {
                    view = inflater.inflate(R.layout.activity_previous, parent, false);
                    previousHolder = new PreviousViewHolder();
                    previousHolder.previous = (TextView) view.findViewById(R.id.previous);
                    view.setTag(previousHolder);
                } else {
                    previousHolder = (PreviousViewHolder) view.getTag();
                }
                previousHolder.previous.setText("Previous " + position);
                break;
            case OWN:
                if (view == null) {
                    view = inflater.inflate(R.layout.activity_own, parent, false);
                    ownHolder = new OwnViewHolder();
                    ownHolder.own = (TextView) view.findViewById(R.id.own);
                    view.setTag(ownHolder);
                } else {
                    ownHolder = (OwnViewHolder) view.getTag();
                }
                ownHolder.own.setText("Own " + position);
                break;
            case BOOKED:
                if (view == null) {
                    view = inflater.inflate(R.layout.activity_adapter, parent, false);
                    bookedHolder = new ViewHolder();
                    bookedHolder.workoutType = (TextView) view.findViewById(R.id.pass);
                    bookedHolder.gymLocation = (TextView) view.findViewById(R.id.center);
                    bookedHolder.instructorsName = (TextView) view.findViewById(R.id.instructor);
                    bookedHolder.positionInQueue = (TextView) view.findViewById(R.id.number_participants);
                    bookedHolder.startTimeHour = (TextView) view.findViewById(R.id.hour);
                    bookedHolder.startTimeMinutes = (TextView) view.findViewById(R.id.minutes);
                    bookedHolder.activityDuration = (TextView) view.findViewById(R.id.class_time);
                    bookedHolder.activityDate = (TextView) view.findViewById(R.id.date_header);
                    bookedHolder.positionInQueueImg = (ImageView) view.findViewById(R.id.img_participants);
                    view.setTag(bookedHolder);
                } else {
                    bookedHolder = (ViewHolder) view.getTag();
                }
                bookedHolder.workoutType.setText(activities.get(position).subType);
                bookedHolder.gymLocation.setText(activities.get(position).booking.center);
//                bookedHolder.gymLocation.setText("CenterName TODO");
                bookedHolder.instructorsName.setText(activities.get(position).booking.aClass.instructorId);
//                bookedHolder.instructorsName.setText("instructorName TODO");
                bookedHolder.positionInQueue.setText(Integer.toString(activities.get(position).booking.positionInQueue));
//                bookedHolder.positionInQueue.setText("PosInQueue TODO");
                bookedHolder.startTimeHour.setText(activities.get(position).date.substring(11, 13));
                bookedHolder.startTimeMinutes.setText(activities.get(position).date.substring(14, 16));
//                bookedHolder.activityDate.setText(activities.get(position).date.substring(0, 10));
                bookedHolder.activityDuration.setText(activities.get(position).durationInMinutes + " min");

                // Hide waiting list count img if no one in queue
            if (activities.get(position).booking.positionInQueue == 0) {
                bookedHolder.positionInQueue.setVisibility(View.GONE);
                bookedHolder.positionInQueueImg.setVisibility(View.GONE);
            }
                break;

        }


        return view;
    }
    
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
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

    public static ArrayList<Activity> getList() {
        return activities;
    }

    @Override
    public long getHeaderId(int i) {
        return i;
    }

    private class HeaderViewHolder {
        TextView text;
    }
}