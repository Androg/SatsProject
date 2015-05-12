package se.tuppload.android.satstrainingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.DateTime;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends ActionBarActivity {

    StickyListHeadersListView searchList = null;

    DateTime date = new DateTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.refresh).setOnClickListener(refreshJson);

        searchList = (StickyListHeadersListView) findViewById(R.id.sticky_list);

        RequestJson.getJsonData(searchList, this);

        searchList.setOnStickyHeaderChangedListener(new StickyListHeadersListView.OnStickyHeaderChangedListener() {
            @Override
            public void onStickyHeaderChanged(StickyListHeadersListView stickyListHeadersListView, View header, int i, long l) {
                TextView txt = (TextView) findViewById(R.id.workout_tense);
                if (date.isAfter(Long.parseLong(TrainingListAdapter.getList().get(i).mStartTime))) {
                    txt.setText("TIDIGARE TRÄNING");
                } else {
                    txt.setText("KOMMANDE TRÄNING");
                }
            }
        });

    }

    private View.OnClickListener refreshJson = new View.OnClickListener() {
        public void onClick(View view) {
            ImageView animationTarget = (ImageView) view.findViewById(R.id.refresh);

            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.animator.refresh);
            animationTarget.startAnimation(animation);

            RequestJson.getJsonData(searchList, MainActivity.this);
        }
    };
}
