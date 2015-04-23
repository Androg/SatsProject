package se.tuppload.android.satstrainingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
=======
        RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams((RelativeLayout.LayoutParams.WRAP_CONTENT), (int)(400));

>>>>>>> d96115387ce249b9d6cd108c43f0b87650cfec31
        SATSClass[] satsClasses = new SATSClass[10];

        satsClasses[0] = new SATSClass("curling", "throwing stones on ice");
        satsClasses[1] = new SATSClass("Ice hockey", "throwing pucks on ice");
        satsClasses[2] = new SATSClass("fotball", "throwing balls on grass");
        satsClasses[3] = new SATSClass("tennis", "hitting balls with racket");
        satsClasses[4] = new SATSClass("tennis", "hitting balls with racket");
        satsClasses[5] = new SATSClass("tennis", "hitting balls with racket");
        satsClasses[6] = new SATSClass("tennis", "hitting balls with racket");
        satsClasses[7] = new SATSClass("tennis", "hitting balls with racket");
        satsClasses[8] = new SATSClass("tennis", "hitting balls with racket");
        satsClasses[9] = new SATSClass("tennis", "hitting balls with racket");

        SATSAdapter adapter = new SATSAdapter(this, R.layout.simple_item, satsClasses);

        ListView listViewItems = new ListView(this);
        listViewItems.setLayoutParams(mParam);
        listViewItems.setAdapter(adapter);

        setContentView(listViewItems);

    }
}
