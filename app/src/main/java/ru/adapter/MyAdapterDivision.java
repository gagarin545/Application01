package ru.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.example.application01.R;
import ru.entity.Division;

import static ru.activity.DivisionsActivity.divisions;

public class MyAdapterDivision extends ArrayAdapter implements ListAdapter {
    public MyAdapterDivision(@NonNull Context context) {
        super(context, R.layout.list_item, divisions);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Division division = (Division) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_activated_2, null);
        }
        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(division.getNamedivision());
        ((TextView) convertView.findViewById(android.R.id.text2)).setText('(' + String.valueOf(division.getIdcity()) + ')');
        return convertView;
    }
}
