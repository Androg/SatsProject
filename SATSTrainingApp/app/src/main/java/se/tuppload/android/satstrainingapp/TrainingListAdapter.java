package se.tuppload.android.satstrainingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.tuppload.android.satstrainingapp.Holders.OwnViewHolder;
import se.tuppload.android.satstrainingapp.Holders.PreviousViewHolder;
import se.tuppload.android.satstrainingapp.Holders.BookedViewHolder;
import se.tuppload.android.satstrainingapp.models.Activity;
import se.tuppload.android.satstrainingapp.models.Center;

public class TrainingListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private static ArrayList<Activity> activities = new ArrayList<>();
    private HashMap<String, Center> centers = new HashMap<>();
    private LayoutInflater inflater;
    private final String[] weekDay = {"", "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
    private final String[] month = {"", "Januari", "Februari", "Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September", "Oktober", "November", "December"};
    private DateTime date;
    private DateTime currentDateTime = new DateTime();
    private DateTime activityDate;
    private static final int VIEWTYPE_COUNT = 3;
    private static final int PREVIOUS = 0;
    private static final int BOOKED = 1;
    private static final int OWN = 2;


    public TrainingListAdapter(MainActivity activity, ArrayList<Activity> activities, HashMap centers) {
        inflater = LayoutInflater.from(activity);
        this.activities = activities;
        this.centers = centers;
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
        BookedViewHolder bookedHolder;
        date = DateTime.parse(getItem(position).date);


        int viewType = getItemViewType(position);
        switch (viewType) {
            case PREVIOUS:
                if (view == null) {
                    view = inflater.inflate(R.layout.activity_previous, parent, false);
                    previousHolder = new PreviousViewHolder();
                    previousHolder.type = (TextView) view.findViewById(R.id.previous_type);
                    previousHolder.date = (TextView) view.findViewById(R.id.previous_date);
//                    switch (type)
                    previousHolder.typeImg = (ImageView) view.findViewById(R.id.previous_type_img);
                    view.setTag(previousHolder);
                } else {
                    previousHolder = (PreviousViewHolder) view.getTag();
                }
                previousHolder.type.setText(getItem(position).subType);
                previousHolder.date.setText(weekDay[date.getDayOfWeek()] + " " + date.getDayOfMonth() + "/" + date.getMonthOfYear());

                break;
            case OWN:
                if (view == null) {
                    view = inflater.inflate(R.layout.activity_own, parent, false);
                    ownHolder = new OwnViewHolder();
                    ownHolder.type = (TextView) view.findViewById(R.id.own_type);
                    ownHolder.duration = (TextView) view.findViewById(R.id.own_duration);
                    view.setTag(ownHolder);
                } else {
                    ownHolder = (OwnViewHolder) view.getTag();
                }
                ownHolder.type.setText(getItem(position).subType);
                ownHolder.duration.setText(Integer.toString(getItem(position).durationInMinutes));
                break;
            case BOOKED:
                if (view == null) {
                    view = inflater.inflate(R.layout.activity_booked, parent, false);
                    bookedHolder = new BookedViewHolder();
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
                    bookedHolder = (BookedViewHolder) view.getTag();
                }
                bookedHolder.workoutType.setText(getItem(position).subType);
                bookedHolder.gymLocation.setText(centers.get(getItem(position).booking.center).name);
                bookedHolder.instructorsName.setText(getItem(position).booking.aClass.instructorId);
                bookedHolder.positionInQueue.setText(Integer.toString(getItem(position).booking.positionInQueue));
                bookedHolder.startTimeHour.setText(getItem(position).date.substring(11, 13));
                bookedHolder.startTimeMinutes.setText(getItem(position).date.substring(14, 16));
                bookedHolder.activityDuration.setText(getItem(position).durationInMinutes + " min");

                if (getItem(position).booking.positionInQueue == 0) {
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
        int week = DateTime.parse(getItem(position).date).plusDays(1).getWeekOfWeekyear();
        if (getItemViewType(position) == PREVIOUS) {
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.date_header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.date_header);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            DateTime date = new DateTime(getItem(position).date);
            holder.text.setText(Integer.toString(date.getWeekOfWeekyear()));

//            setHeaderByWeek(position, convertView, holder);

            if (position < getCount()) {
                int newWeek = DateTime.parse(getItem(position + 1).date).plusDays(1).getWeekOfWeekyear();
                if (week == newWeek && getItemViewType(position+1) == PREVIOUS) {
                    holder.text.setVisibility(convertView.GONE);
                }
            }
            return convertView;
        }

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.date_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.date_header);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
//        date = DateTime.parse(getItem(position).date);
        String extra = "";

        if (date.getDayOfYear() == currentDateTime.getDayOfYear()) {
            extra = "Idag, ";
        }
        if (date.getDayOfYear() == currentDateTime.getDayOfYear() + 1) {
            extra = "Imorgon, ";
        }
        String headerText = weekDay[date.getDayOfWeek()] + " " + date.getDayOfMonth() + " " + month[date.getMonthOfYear()];

        holder.text.setText(extra + headerText);
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

    public static void setHeaderByWeek(int position, View view, HeaderViewHolder holder) {
        DateTime date;
        int week = DateTime.parse(activities.get(position).date).plusDays(1).getWeekOfWeekyear();
        date = DateTime.parse(activities.get(position).date).plusDays(1);
        if (position < activities.size() && position > 0) {
            int newWeek = DateTime.parse(activities.get(position + 1).date).plusDays(1).getWeekOfWeekyear();
            int previousWeek = DateTime.parse(activities.get(position - 1).date).plusDays(1).getWeekOfWeekyear();
            if (week == newWeek) {
                holder.text.setVisibility(view.GONE);
            }
        }

    }

}