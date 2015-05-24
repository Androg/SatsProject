package se.tuppload.android.satstrainingapp;

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

public class MainActivity extends ActionBarActivity
{
    public static boolean temp = false;
    public boolean truedat = true;
    StickyListHeadersListView listView = null;
    long startEndDate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final ViewPager graph;
        ColoumnAdapter graphAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_training_listview);
        final TextView txtStatus = (TextView) findViewById(R.id.activity_status);

        final ImageView im = (ImageView) findViewById(R.id.refresh);
        final Animation animRot = AnimationUtils.loadAnimation(this, R.anim.refresh);

        final DateTime currentWeek = new DateTime();

        final StickyListHeadersListView listView = (StickyListHeadersListView) findViewById(R.id.listan);
        RequestJson.getJsonData(listView, this);

        graph = (ViewPager) findViewById(R.id.graph);
        graphAdapter = new ColoumnAdapter();
        graph.setAdapter(graphAdapter);


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
                TextView txt = (TextView) findViewById(R.id.date_header);
                DateTime d = new DateTime(TrainingListAdapter.getList().get(i).date);
                startEndDate = d.getMillis();
                if (d.isBeforeNow()) {
                    txtStatus.setText("TIDIGARE TRÄNING");
                } else {
                    txtStatus.setText("KOMMANDE TRÄNING");
                }
            }
        });

        graph.postDelayed(new Runnable() {
            @Override
            public void run() {
                graph.setCurrentItem(currentWeek.getWeekOfWeekyear() - 2);
            }
        }, 1500);

    }
}



