package ru.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.application01.R;

import java.sql.Timestamp;
import java.util.Calendar;

import static ru.service.GetIncidents.incidents;

public class MyAdapterIncident extends ArrayAdapter implements ListAdapter {
    public static Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());


    public MyAdapterIncident(@NonNull Context context) {
        super(context, R.layout.list_item, incidents);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        @SuppressLint("SimpleDateFormat") Date controlTerm = null;
        int hours = 0 , min = 0;
        String item;
        if( incidents.get(position).getControlterm() > 0) {
            hours = (int)((incidents.get(position).getControlterm() - time.getTime()) / 3600000L);
            min = (int) (((incidents.get(position).getControlterm() - time.getTime()) - (long) (hours) * 3600000L) / 60000);
            item = incidents.get(position).getTypeincident() + incidents.get(position).getN_incident() + " " + Math.abs(hours) + "ч." + Math.abs(min) + "мин." + " " + incidents.get(position).getClazz() + " " + incidents.get(position).getAddress() + " " + incidents.get(position).getRoom();
        } else
            item = incidents.get(position).getTypeincident() + incidents.get(position).getN_incident() + " " + incidents.get(position).getClazz() + " " + incidents.get(position).getAddress() + " " + incidents.get(position).getRoom();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()) .inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setText(item);
        tv.setTextColor( Color.BLUE);
        if (hours < 5)  tv.setTextColor(Color.parseColor("#FF8C00"));
        if (min < 0)    tv.setTextColor(Color.RED);
        if (incidents.get(position).getClazz().contains("ЮЛ"))  tv.setTextColor(Color.parseColor("#D2691E"));
        return convertView;
    }
}
