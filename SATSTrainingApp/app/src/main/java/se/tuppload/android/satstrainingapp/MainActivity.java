package se.tuppload.android.satstrainingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SATSClass[] satsClasses = new SATSClass[4];

        satsClasses[0] = new SATSClass("curling", "throwing stones on ice");
        satsClasses[1] = new SATSClass("Ice hockey", "throwing pucks on ice");
        satsClasses[2] = new SATSClass("fotball", "throwing balls on grass");
        satsClasses[3] = new SATSClass("tennis", "hitting balls with racket");

        SATSAdapter adapter = new SATSAdapter(this, R.layout.simple_item, satsClasses);

        ListView listViewItems = new ListView(this);
        listViewItems.setAdapter(adapter);

        setContentView(listViewItems);

    }
}
