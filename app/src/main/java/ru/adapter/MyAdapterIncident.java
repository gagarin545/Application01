package ru.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.example.application01.R;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static ru.activity.MainActivity.incidents;

public class MyAdapterIncident extends ArrayAdapter implements ListAdapter {
    public static Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());

    public MyAdapterIncident(@NonNull Context context) {
        super(context, R.layout.list_item, incidents);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int hours = 0 , min = 0;
        String item, text = " ", clazz="<font color='#EE0000'><small><b> (" ;
        DateFormat df = new SimpleDateFormat("HH:mm dd.mm.yy");
        if( incidents.get(position).getControlterm() > 0) {
            hours = (int) ((incidents.get(position).getControlterm() - time.getTime()) / 3600000L);
            min = (int) (((incidents.get(position).getControlterm() - time.getTime()) - (long) (hours) * 3600000L) / 60000);
            text = "<font color='#0000FF'><small><b> (" + Math.abs(hours) + "ч." + Math.abs(min) + "мин." + " ) </b></font></small>";
            if (hours < 5) text = "<font color='#FF8C00'><small><b> (" + Math.abs(hours) + "ч." + Math.abs(min) + "мин." + ") </b></font></small>";
            if (min < 0) text = "<font color='#EE0000'><small><b> (" + Math.abs(hours) + "ч." + Math.abs(min) + "мин." + ") </b></font></small>";
        }
        if(incidents.get(position).getClazz().contains("ФЛ B2C") )
            clazz="<font color='#0000FF'><small><b> (";
        item = incidents.get(position).getTypeincident() + incidents.get(position).getN_incident() + text + "<em>до <u>" + df.format(incidents.get(position).getDecisiontime() + 5*60*60*1000) + " </em></u>" + incidents.get(position).getAddress() + " " + incidents.get(position).getRoom() + clazz  + incidents.get(position).getClazz() + ")</b></font></small>";
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()) .inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setText(Html.fromHtml(item));
        if (incidents.get(position).getClazz().contains("ЮЛ"))  tv.setTextColor(Color.parseColor("#D2691E"));
        return convertView;
    }
}
