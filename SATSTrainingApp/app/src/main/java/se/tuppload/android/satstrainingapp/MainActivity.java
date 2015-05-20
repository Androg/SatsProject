package se.tuppload.android.satstrainingapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends ActionBarActivity
{
    public static boolean temp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txtStatus = (TextView) findViewById(R.id.activity_status);
        ViewPager graph;
        CalendarAdapter graphAdapter;


        final ImageView im = (ImageView) findViewById(R.id.refresh);
        final Animation animRot = AnimationUtils.loadAnimation(this, R.anim.refresh);

        final StickyListHeadersListView listView = (StickyListHeadersListView) findViewById(R.id.listan);
        RequestJson.getJsonData(listView, this);

        graph = (ViewPager) findViewById(R.id.graph);
        graphAdapter = new CalendarAdapter();
        graph.setAdapter(graphAdapter);
//        graph.setCurrentItem(new DateTime().getWeekOfWeekyear());


        graph.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int page) {
                //page changed
//                listView.smoothScrollToPosition(page);
                        Log.d("PAGE = " ,""+page);
                for (int i = 0; i < TrainingListAdapter.getList().size(); i++) {
                    if (page-1 == DateTime.parse(TrainingListAdapter.getList().get(i).date).getWeekOfWeekyear()) {
                        listView.smoothScrollToPosition(i+1);
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
//                listView.smoothScrollToPosition(arg0);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
//                listView.smoothScrollToPosition(arg0);
            }
        });

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestJson.getJsonData(listView, MainActivity.this);
                im.startAnimation(animRot);
            }
        });

        listView.setOnStickyHeaderChangedListener(new StickyListHeadersListView.OnStickyHeaderChangedListener() {
            @Override
            public void onStickyHeaderChanged(StickyListHeadersListView stickyListHeadersListView, View header, int i, long l) {
                DateTime d = new DateTime(TrainingListAdapter.getList().get(i).date);
                if (d.isBeforeNow()) {
                    txtStatus.setText("TIDIGARE TRÄNING");
                } else {
                    txtStatus.setText("KOMMANDE TRÄNING");
                }
            }
        });
    }

}


