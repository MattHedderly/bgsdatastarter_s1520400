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

        View view = layoutInflater.inflate(layoutResource, parent,false);
        TextView location = (TextView) view.findViewById(R.id.textLocation);
        TextView date = (TextView) view.findViewById(R.id.textDate);
        TextView magnitude = (TextView) view.findViewById(R.id.textMagnitude);

        Earthquake currentQuake = earthquakes.get(position);


        //check the scroller is working
        Log.d(TAG, "getView: location of quake is " + currentQuake.getLocation());

        //set the attributes I want for the home view
        location.setText(currentQuake.getLocation());
        date.setText(currentQuake.getDate());
        magnitude.setText(currentQuake.getMagnitude());

        return view;
    }
}
