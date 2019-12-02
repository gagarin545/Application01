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
import java.text.SimpleDateFormat;
import java.util.Date;
import static ru.activity.MainActivity.incidents;

public class MyAdapterIncidentList extends ArrayAdapter implements ListAdapter {

    public MyAdapterIncidentList(@NonNull Context context) {
        super(context, R.layout.list_item, incidents);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String text= " ";
        SimpleDateFormat format = new SimpleDateFormat(" HH:mm dd.MM.yy");
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()) .inflate(android.R.layout.simple_list_item_2, null);
        }
        TextView tv = convertView.findViewById(android.R.id.text1);
        if( incidents.get(position).getControlterm() > 0) {
            if (((incidents.get(position).getControlterm() + 7200000L) - incidents.get(position).getDecisiontime()) > 0)
                text = "<font color='#33BBFF'><small><b> (до " + format.format(new Date(incidents.get(position).getDecisiontime())) + ") </b></font></small>";
            else
                text = "<font color='#FF4019'><small><b> (до " + format.format(new Date(incidents.get(position).getDecisiontime())) + ") </b></font></small>";
        }
        tv.setText(Html.fromHtml(incidents.get(position).getTypeincident() + incidents.get(position).getN_incident() + text + incidents.get(position).getAddress() + " " + incidents.get(position).getRoom() + "<small>(" + incidents.get(position).getClazz() + ")</small>"));
        if (incidents.get(position).getClazz().contains("ЮЛ"))  tv.setTextColor(Color.parseColor("#D2691E"));
        return convertView;
    }
}
