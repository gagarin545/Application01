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
import java.text.SimpleDateFormat;
import java.util.Date;
import static ru.activity.MainActivity.incidents;

public class MyAdapterIncidentRepet extends ArrayAdapter implements ListAdapter {

    public MyAdapterIncidentRepet(@NonNull Context context) {
        super(context, R.layout.list_item, incidents);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()) .inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView tv = convertView.findViewById(android.R.id.text1);
        if( incidents.get(position).getControlterm() > 0) {
            if (((incidents.get(position).getControlterm() + 7200000L) - incidents.get(position).getDecisiontime()) > 0)
                tv.setTextColor(Color.parseColor("#33BBFF"));
            else
                tv.setTextColor(Color.parseColor("#FF4019"));
        }
        tv.setText(incidents.get(position).getTypeincident() + incidents.get(position).getN_incident() + " " + format.format(new Date(incidents.get(position).getDecisiontime())) + " " + incidents.get(position).getClazz() + " " + incidents.get(position).getAddress() + " " + incidents.get(position).getRoom());
        if (incidents.get(position).getClazz().contains("ЮЛ"))  tv.setTextColor(Color.parseColor("#D2691E"));
        return convertView;
    }
}
