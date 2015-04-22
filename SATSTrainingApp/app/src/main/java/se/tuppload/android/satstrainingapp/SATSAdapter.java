package se.tuppload.android.satstrainingapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class SATSAdapter extends ArrayAdapter<SATSClass> {

    Context mContext;
    int layoutResourceId;
    SATSClass data[] = null;

    public SATSAdapter(Context mContext, int layoutResourceId, SATSClass[] data) {

        super(mContext, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        SATSClass sats = data[position];

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItem = (TextView) convertView.findViewById(R.id.class_name);
        TextView textViewItem2 = (TextView) convertView.findViewById(R.id.class_description);
        textViewItem.setText(sats.getName());
        textViewItem2.setText(sats.getDescription());

        return convertView;

    }

}