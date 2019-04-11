package com.example.bgsdatastarter_s1520400;

// Name                 Matt Hedderly
// Student ID           S1520400
// Programme of Study   Computing

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class QuakeAdapter extends ArrayAdapter {
    private static final String TAG = "QuakeAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Earthquake> earthquakes;

    public QuakeAdapter(Context context, int resource, List<Earthquake> earthquakes) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.earthquakes = earthquakes;
    }

    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        //improve memory performance by reusing the views that are scrolled off the screen instead of creating new
        //if it's not got any create a new view
        if(convertView == null){
            convertView = layoutInflater.inflate(layoutResource, parent,false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Earthquake currentQuake = earthquakes.get(position);


        //check the scroller is working
        Log.d(TAG, "getView: location of quake is " + currentQuake.getLocation());

        //set the attributes I want for the home view
        viewHolder.location.setText(currentQuake.getLocation());
        viewHolder.date.setText(currentQuake.getDate());
        viewHolder.magnitude.setText(currentQuake.getMagnitude());

        return convertView;
    }

    //set up view holder pattern to improve efficiency
    private class ViewHolder{
        final TextView location;
        final TextView date;
        final TextView magnitude;

        ViewHolder(View v){
            this.location = v.findViewById(R.id.textLocation);
            this.date = v.findViewById(R.id.textDate);
            this.magnitude = v.findViewById(R.id.textMagnitude);
        }
    }
}
