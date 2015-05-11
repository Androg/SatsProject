package se.tuppload.android.satstrainingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends ActionBarActivity {

    StickyListHeadersListView searchList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageView)this.findViewById(R.id.refresh)).setOnClickListener(refreshJson);

        searchList = (StickyListHeadersListView) findViewById(R.id.sticky_list);
        RequestJson.getJsonData(searchList, this);
        searchList.setOnStickyHeaderChangedListener(new StickyListHeadersListView.OnStickyHeaderChangedListener() {
            @Override
            public void onStickyHeaderChanged(StickyListHeadersListView stickyListHeadersListView, View view, int i, long l) {

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
