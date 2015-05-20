package se.tuppload.android.satstrainingapp;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ColoumnAdapter extends PagerAdapter
{
    static List<Integer> workoutsPerWeek = new ArrayList<Integer>();
    static Map<Integer, Integer> workoutPerWeekDone = new HashMap<>();
    DateTime dateToday = new DateTime();
    DateTime date = new DateTime();
    DateTime date2;
    public static float earlierPos = 0;
    int prevCellPosition;
    int nextCellPosition;
    Calendar calendar = Calendar.getInstance();

    int NumberOfPages = 52;


    @Override
    public int getCount() {

        return NumberOfPages;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //Gets the context for calendar
        RelativeLayout viewContext = new RelativeLayout(container.getContext());
//        ViewPager calenderPager = (ViewPager) container.findViewById(R.id.graph);

        //TextView week Displays dates in the week
        TextView week = new TextView(container.getContext());
        week.setBackgroundColor(container.getResources().getColor(R.color.white));

        //Get relative layout set parameters
        RelativeLayout parent = (RelativeLayout) container.findViewById(R.id.my_training);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, week.getId());

        //Set layout parameters to textView week
        week.setLayoutParams(params);

        //LayoutParams weekParams handles parameters set to textView
        LayoutParams weekParams = week.getLayoutParams();

        //Changes weekParams height
        weekParams.height = 95;

        if(position != 0 && workoutPerWeekDone.containsKey(position - 1)) {
            prevCellPosition = workoutPerWeekDone.get(position - 1);
        } else {
            prevCellPosition = -1;
        }

        Log.e("Position ------", String.valueOf(position));
        if(position < 50 && workoutPerWeekDone.containsKey(position + 1)) {
            nextCellPosition = workoutPerWeekDone.get(position + 1);
        } else {
            nextCellPosition = -1;
        }

//        getDate(position);

        DateTime weekStartDate = new DateTime().withWeekOfWeekyear(position + 1).minusDays(dateToday.getDayOfWeek() + 6);
        DateTime weekEndDate = new DateTime().withWeekOfWeekyear(position + 1).minusDays(dateToday.getDayOfWeek());

        //Sets text day1 - day2 / day1month
//        week.setText();
        week.setText(weekStartDate.getDayOfMonth() + "-" + weekEndDate.getDayOfMonth() + "/" +
                weekStartDate.getMonthOfYear());

        //Moves week to center
        week.setGravity(Gravity.CENTER);

        //Adds week to the views Context
        viewContext.addView(week);

        //Paints out position
        if(position < dateToday.getWeekOfWeekyear() && workoutPerWeekDone.containsKey(position)) {
            // Context, Filled, Position
            Coloumn text = new Coloumn(container.getContext(), true, workoutPerWeekDone.get(position),
                    nextCellPosition, position + 1 < dateToday.getWeekOfWeekyear(), prevCellPosition, true);
            viewContext.addView(text);

        } else if(position == dateToday.getWeekOfWeekyear()) {

            ImageView top = new ImageView(container.getContext());
            top.setImageResource(R.drawable.now_marker);
            Coloumn text = new Coloumn(container.getContext(), false, workoutPerWeekDone.get(position),
                    nextCellPosition, false, prevCellPosition, false);

            //Scale current week banner
            top.setScaleX(0.7f);
            top.setScaleY(0.7f);
            top.setPadding(65, 0, 0, 0);
            top.setY(-20);

            top.setScaleType(ImageView.ScaleType.CENTER);

            viewContext.addView(top);
            viewContext.addView(text);

        } else if(position > dateToday.getWeekOfWeekyear() && workoutPerWeekDone.containsKey(position)) {
            Coloumn text = new Coloumn(container.getContext(), false, workoutPerWeekDone.get(position),
                    nextCellPosition, false, prevCellPosition, false);

            text.bringToFront();
            viewContext.addView(text);
        }

        RelativeLayout layout = new RelativeLayout(container.getContext());
        RelativeLayout.LayoutParams x = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        viewContext.setLayoutParams(x);
        layout.setLayoutParams(x);

//        layout.setBackground(container.getResources().getDrawable(R.drawable.calendar_dark, null));

        layout.addView(viewContext);


        if(position % 2 == 0) {
//            layout.setBackground(container.getResources().getDrawable(R.drawable.calendar_light, null));
        }
        if(position > 1) {

        }

        final int page = position;


        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
    @Override
    public float getPageWidth(int position) {
        float nbPages = 5; // You could display partial pages using a float value
        return (1 / nbPages);
    }

    public static void setArrayList(ArrayList<Integer> array) {
        workoutsPerWeek = array;
        Collections.sort(workoutsPerWeek);
        countArrayListPerWeek();
    }

    public static void countArrayListPerWeek() {
        for(Integer key : workoutsPerWeek) {
            workoutPerWeekDone.put(key, Collections.frequency(workoutsPerWeek, key));
        }

        //Prints out Key + Value for every week in workoutPerWeekDone
        for(Map.Entry<Integer, Integer> key : workoutPerWeekDone.entrySet()) {
            Log.e("TestingTesting", String.valueOf(key.getKey() + "--------" + key.getValue()));
        }

    }

//    public static int getDate(int position) {
//
//        Log.e("Hejsan hoppsan", workoutPerWeekDone.keySet().toString());
//
//        return 0;
//    }
}
