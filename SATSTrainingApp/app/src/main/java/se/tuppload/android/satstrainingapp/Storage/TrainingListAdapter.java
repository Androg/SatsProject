package se.tuppload.android.satstrainingapp.Storage;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.tuppload.android.satstrainingapp.Holders.BookedViewHolder;
import se.tuppload.android.satstrainingapp.Holders.OwnViewHolder;
import se.tuppload.android.satstrainingapp.Holders.PreviousViewHolder;
<<<<<<< HEAD:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/Storage/TrainingListAdapter.java
import se.tuppload.android.satstrainingapp.Holders.BookedViewHolder;
import se.tuppload.android.satstrainingapp.Models.Activity;
import se.tuppload.android.satstrainingapp.R;
=======
import se.tuppload.android.satstrainingapp.Model.*;
>>>>>>> 1854c0d03c7b0f9af84fb1bf35223ef0c4d46494:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/TrainingListAdapter.java

public class TrainingListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private static ArrayList<Activity> activities = new ArrayList<>();
    private static ArrayList<Integer> activitiesPerWeek = new ArrayList<>();
    private HashMap<String, Center> centers = new HashMap<>();
    private LayoutInflater inflater;
<<<<<<< HEAD:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/Storage/TrainingListAdapter.java
    private android.app.Activity activity;
    private final int numberOfPositions;
    private Calendar mCalendar = Calendar.getInstance();
    private final String[] weekDay = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
    private final String[] month = {"Januari", "Februari", "Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September", "Oktober", "November", "December"};
    private DateTime currentDateTime = new DateTime();
    private DateTime date;
    private DateTime date2;
=======
    private final String[] weekDay = {"", "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
    private final String[] month = {"", "Januari", "Februari", "Mars", "April", "Maj", "Juni", "Juli", "Augusti", "September", "Oktober", "November", "December"};
    private DateTime dateToday = new DateTime();
>>>>>>> 1854c0d03c7b0f9af84fb1bf35223ef0c4d46494:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/TrainingListAdapter.java
    private DateTime activityDate;
    private DateTime date;
    private DateTime date2;
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
        final PreviousViewHolder previousHolder;
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
                    previousHolder.checkBox = (CheckBox) view.findViewById(R.id.completedOrNot);
                    previousHolder.typeImg = (ImageView) view.findViewById(R.id.previous_type_img);
                    view.setTag(previousHolder);
                } else {
                    previousHolder = (PreviousViewHolder) view.getTag();
                }

                previousHolder.type.setText(activities.get(position).type);
                previousHolder.date.setText(activities.get(position).date.substring(0, 10));

<<<<<<< HEAD:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/Storage/TrainingListAdapter.java
=======
                previousHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (previousHolder.checkBox.isChecked()) {
                            previousHolder.checkBox.setText("Avklarat!");
                        } else {
                            previousHolder.checkBox.setText("Avklarat?");
                        }
                    }
                });

>>>>>>> 1854c0d03c7b0f9af84fb1bf35223ef0c4d46494:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/TrainingListAdapter.java

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
<<<<<<< HEAD:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/Storage/TrainingListAdapter.java
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

                    LinearLayout lay = (LinearLayout) view.findViewById(R.id.right_inner_box);

                    lay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent moreInfo = new Intent(TrainingListAdapter.this.activity, ShowActivityInfo.class);
                            TrainingListAdapter.this.activity.startActivity(moreInfo);
                        }
                    });
                }

                break;
=======
                bookedHolder.workoutType.setText(getItem(position).subType);
                bookedHolder.gymLocation.setText(centers.get(getItem(position).booking.center).name);
                bookedHolder.instructorsName.setText(getItem(position).booking.aClass.instructorId);
                bookedHolder.positionInQueue.setText(Integer.toString(getItem(position).booking.positionInQueue));
                bookedHolder.startTimeHour.setText(getItem(position).date.substring(11, 13));
                bookedHolder.startTimeMinutes.setText(getItem(position).date.substring(14, 16));
                bookedHolder.activityDuration.setText(getItem(position).durationInMinutes + " min");
>>>>>>> 1854c0d03c7b0f9af84fb1bf35223ef0c4d46494:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/TrainingListAdapter.java

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
<<<<<<< HEAD:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/Storage/TrainingListAdapter.java

        if (getItemViewType(position) == PREVIOUS) {
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.date_header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.date_header);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            date = DateTime.parse(getItem(position).date);

            if (date.getDayOfYear() < currentDateTime.getDayOfYear()) {
                int dateFormat;

                if ((date.getDayOfMonth() + 6) >= 32) {
                    dateFormat = (date.getDayOfMonth() + 5);
                } else {
                    dateFormat = (date.getDayOfMonth() + 6);
                }

                date2 = DateTime.parse(getItem(position).date);

                if(position > 0) {
                    date2 = DateTime.parse(getItem(position - 1).date);
                    int currentWeek = date.getWeekOfWeekyear();
                    int previousWeek = date2.getWeekOfWeekyear();

                    if (currentWeek == previousWeek) {
                        holder.text.setVisibility(View.INVISIBLE);
                    } else if(currentWeek != previousWeek){
                        holder.text.setText("Vecka " + date.getWeekOfWeekyear() + " (" + date.getDayOfMonth() + "-" +
                                dateFormat + "/" + date.getMonthOfYear() + ")");
                    }
                } else {
                    holder.text.setText("Vecka " + date.getWeekOfWeekyear() + " (" + date.getDayOfMonth() +
                            "-" + dateFormat + "/" + date.getMonthOfYear() + ")");
                }
            }

            return convertView;
        } else {
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.date_header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.date_header);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            date = DateTime.parse(getItem(position).date);

            if (date.getDayOfYear() > currentDateTime.getDayOfYear()) {
                String headerText = weekDay[date.getDayOfWeek()] + " " + date.getDayOfMonth() + " " + month[date.getMonthOfYear()];
                holder.text.setText(headerText);
            }

=======
        DateTime activityFullDate = new DateTime(activities.get(position).date);
        DateTime activityWeek = new DateTime().withWeekOfWeekyear(activityFullDate.getDayOfMonth());
        DateTime activityDateStart = new DateTime().withWeekOfWeekyear(position + 1).minusDays(dateToday.getDayOfWeek() + 8);
        DateTime activityDateEnd = new DateTime().withWeekOfWeekyear(position + 1).minusDays(dateToday.getDayOfWeek() + 2);

        if(MainActivity.temp == false) {
            addToArrayList(activities);
            ColoumnAdapter.setArrayList(activitiesPerWeek);
            MainActivity.temp = true;
        }

        if (getItemViewType(position) == PREVIOUS) {

            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.date_header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.date_header);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }

//            date = DateTime.parse(getItem(position).date);

            if (date.getDayOfYear() < dateToday.getDayOfYear()) {

                date2 = DateTime.parse(getItem(position).date);

                if(position > 0) {
                    date2 = DateTime.parse(getItem(position - 1).date);
                    int currentWeek = date.getWeekOfWeekyear();
                    int previousWeek = date2.getWeekOfWeekyear();

                    if (currentWeek == previousWeek) {
                        holder.text.setVisibility(View.GONE);
                    } else if(currentWeek != previousWeek){
                        holder.text.setText("Vecka " + activityFullDate.getWeekOfWeekyear() + " (" + (activityDateStart.getDayOfMonth()) + "-" +
                                (activityDateEnd.getDayOfMonth()) + "/" + activityDate.getMonthOfYear() + ")");
                    }
                } else {
                    holder.text.setText("Vecka " + activityFullDate.getWeekOfWeekyear() + " (" + (activityDateStart.getDayOfMonth()) + "-" +
                            (activityDateEnd.getDayOfMonth()) + "/" + activityDate.getMonthOfYear() + ")");
                }
            }
            return convertView;
        } else {
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.date_header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.date_header);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            date = DateTime.parse(getItem(position).date);

            if (date.getDayOfYear() > dateToday.getDayOfYear()) {
                String headerText = weekDay[date.getDayOfWeek()] + " " + date.getDayOfMonth() + " " + month[date.getMonthOfYear()];
                holder.text.setText(headerText);
            }
>>>>>>> 1854c0d03c7b0f9af84fb1bf35223ef0c4d46494:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/TrainingListAdapter.java
            return convertView;
        }
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

<<<<<<< HEAD:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/Storage/TrainingListAdapter.java
=======
    public void addToArrayList(ArrayList<Activity> temp) {
        for(Activity tempInt : temp) {
            date = DateTime.parse(tempInt.date);
            activitiesPerWeek.add(Integer.valueOf(date.getWeekOfWeekyear()));
        }

    }
>>>>>>> 1854c0d03c7b0f9af84fb1bf35223ef0c4d46494:SATSTrainingApp/app/src/main/java/se/tuppload/android/satstrainingapp/TrainingListAdapter.java
}