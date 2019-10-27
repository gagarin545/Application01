package ru.util;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.example.application01.R;

import java.util.List;

public class ArrayAdpterList<L> extends BaseAdapter implements ListAdapter {

    private Context context;
    private List<ListItem> items;

    public ArrayAdpterList(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ListItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem item = getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        ConstraintLayout layout = convertView.findViewById(R.id.layout);
        Integer colorRes;
        switch (item.getType()) {
            default:
            case R.color.RED:
                colorRes = context.getResources().getColor(R.color.RED);
                break;
            case R.color.Green:
                colorRes = context.getResources().getColor(R.color.Green);
                break;
            case R.color.Blue:
                colorRes = context.getResources().getColor(R.color.Blue);
                break;
            case R.color.Yellow:
                colorRes = context.getResources().getColor(R.color.Yellow);
                break;
            case R.color.DarkSlateGray:
                colorRes = context.getResources().getColor(R.color.DarkSlateGray);
                break;
        }
        layout.setBackgroundColor(context.getResources().getColor(R.color.RED));
        return convertView;
    }
}

