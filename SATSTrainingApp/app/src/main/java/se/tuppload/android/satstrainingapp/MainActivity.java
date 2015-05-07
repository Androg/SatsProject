package se.tuppload.android.satstrainingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    ListView searchList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((ImageButton)this.findViewById(R.id.refresh)).setOnClickListener(refreshJson);

        searchList = (ListView) findViewById(R.id.list_view);
        RequestJson.getJsonData(searchList, this);

    }

    private View.OnClickListener refreshJson = new View.OnClickListener() {
        public void onClick(View view) {
            ImageButton animationTarget = (ImageButton) view.findViewById(R.id.refresh);

            Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.animator.refresh);
            animationTarget.startAnimation(animation);

            RequestJson.getJsonData(searchList, MainActivity.this);
        }
    };
}
