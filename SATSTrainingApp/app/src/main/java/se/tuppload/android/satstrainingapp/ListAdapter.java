package se.tuppload.android.satstrainingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class ListAdapter extends ArrayAdapter<Item>{

    public ListAdapter(Context context, int textViewResourceId){
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<Item> items){
        super(context, resource, items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }
        Item p = getItem(position);

        if(p == null){
            TextView t1 = (TextView) v.findViewById(R.id.id);
            TextView t2 = (TextView) v.findViewById(R.id.categoryId);
            TextView t3 = (TextView) v.findViewById(R.id.description);

            if(t1 != null){
                t1.setText(p.getId);
            }
            if (t2!=null){
                t2.setText(p.getCategory);
            }
            if (t3 != null){
                t3.setText(p.getDescription);
            }
        }
        return v;
    }

}
