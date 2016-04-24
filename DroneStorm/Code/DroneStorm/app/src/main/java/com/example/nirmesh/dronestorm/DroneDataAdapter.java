package com.example.nirmesh.dronestorm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nirmesh on 4/24/2016.
 */
public class DroneDataAdapter extends ArrayAdapter<DroneData>{

    ArrayList<DroneData> ArrayDroneList;
    int Resource;
    Context context;
    LayoutInflater vi;
    public DroneDataAdapter(Context context, int resource, ArrayList<DroneData> objects) {
        super(context, resource, objects);

        ArrayDroneList = objects;
        Resource = resource;
     this.context = context;

        vi = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder holder;
        if(convertView == null){
           convertView = vi.inflate(Resource,null);
            holder = new viewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            holder.sBearing = (TextView)convertView.findViewById(R.id.sBearing);
            holder.sDay = (TextView)convertView.findViewById(R.id.sDay);
            holder.sIntensity =(TextView)convertView.findViewById(R.id.sIntensity);
            holder.sProbability =(TextView)convertView.findViewById(R.id.sProbabilit);
            holder.sTemperature = (TextView)convertView.findViewById(R.id.sTemperature);
            holder.summary = (TextView)convertView.findViewById(R.id.summary);
            holder.sVisibility = (TextView)convertView.findViewById(R.id.sVisibility);
            holder.sWind = (TextView)convertView.findViewById(R.id.sWind);

            convertView.setTag(holder);

        } else {

            holder = (viewHolder)convertView.getTag();
        }

        holder.sWind.setText(ArrayDroneList.get(position).getWind());
        holder.sVisibility.setText(ArrayDroneList.get(position).getVisibility());
        holder.summary.setText(ArrayDroneList.get(position).getSummary());
        holder.sTemperature.setText(ArrayDroneList.get(position).getTemperature());
        holder.sBearing.setText(ArrayDroneList.get(position).getBearing());
        holder.sDay.setText(ArrayDroneList.get(position).getClearDay());
        holder.sProbability.setText(ArrayDroneList.get(position).getProbability());
        holder.sIntensity.setText(ArrayDroneList.get(position).getIntensity());
        holder.speed.setText(ArrayDroneList.get(position).getSpeed());



        return convertView;

    }

    static class viewHolder
    {
        private ImageView imageView;

        private TextView sDay;


        private TextView sIntensity;

        private TextView sProbability;


        private TextView summary;

        private TextView sTemperature;

        private TextView sVisibility;

        private TextView sWind;

        private TextView sBearing;

        private TextView speed;

    }
}
