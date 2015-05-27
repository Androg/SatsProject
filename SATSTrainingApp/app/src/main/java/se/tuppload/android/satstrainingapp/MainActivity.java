package se.tuppload.android.satstrainingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import org.joda.time.DateTime;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import se.tuppload.android.satstrainingapp.Adapter.ColoumnAdapter;
import se.tuppload.android.satstrainingapp.Adapter.TrainingListAdapter;

import static se.tuppload.android.satstrainingapp.R.layout.my_training_listview;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private final DateTime currentWeek = new DateTime();
    public int currentPage = currentWeek.getWeekOfWeekyear();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(my_training_listview);

        final ViewPager calender = (ViewPager) findViewById(R.id.graph);
        final TextView activityStatus = (TextView) findViewById(R.id.activity_status);
        final ImageView refreshButton = (ImageView) findViewById(R.id.refresh);
        final Animation refreshButtonAnimation = AnimationUtils.loadAnimation(this, R.anim.refresh);
        final StickyListHeadersListView listView = (StickyListHeadersListView) findViewById(R.id.listan);
        final ImageView left = (ImageView) this.findViewById(R.id.back_to_now_left);
        final ImageView right = (ImageView) this.findViewById(R.id.back_to_now_right);
        final ImageView view = (ImageView)findViewById(R.id.location_arrow);
        final int distanceFromCenter = 3;

        RequestJson.getJsonData(listView, this);
        calender.setAdapter(new ColoumnAdapter());

        view.setOnClickListener(this);

        calender.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position + 2;

                if (currentPage >= currentWeek.getWeekOfWeekyear() + distanceFromCenter) {
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.GONE);
                } else if (!(currentPage >= currentWeek.getWeekOfWeekyear() + distanceFromCenter)) {
                    left.setVisibility(View.GONE);
                }

                if (currentPage <= currentWeek.getWeekOfWeekyear() - distanceFromCenter) {
                    right.setVisibility(View.VISIBLE);
                    left.setVisibility(View.GONE);
                } else if (!(currentPage <= currentWeek.getWeekOfWeekyear() - distanceFromCenter)) {
                    right.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestJson.getJsonData(listView, MainActivity.this);
                refreshButton.startAnimation(refreshButtonAnimation);

                calender.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calender.setAdapter(new ColoumnAdapter());
                        calender.setCurrentItem(currentWeek.getWeekOfWeekyear() - 2);
                    }
                }, 1500);
            }
        });

        listView.setOnStickyHeaderChangedListener(new StickyListHeadersListView.OnStickyHeaderChangedListener() {
            @Override
            public void onStickyHeaderChanged(StickyListHeadersListView stickyListHeadersListView, View header, int i, long l) {
                DateTime date = new DateTime(TrainingListAdapter.getList().get(i).date);
                if (date.isBeforeNow()) {
                    activityStatus.setText("TIDIGARE TRÄNING");
                } else {
                    activityStatus.setText("KOMMANDE TRÄNING");
                }
            }
        });

        calender.postDelayed(new Runnable() {
            @Override
            public void run() {
                calender.setCurrentItem(currentWeek.getWeekOfWeekyear() - 2);
            }
        }, 1500);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.location_arrow) {
            showMapActivity();
        }
    }

    private void showMapActivity() {
        startActivity(new Intent(".ShowMapActivity"));
    }
}



