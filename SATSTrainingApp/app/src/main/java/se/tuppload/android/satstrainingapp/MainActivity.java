package se.tuppload.android.satstrainingapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView searchList = (ListView) findViewById(R.id.list_view);
        RequestJson.getJsonData(searchList, this);



    }
}
