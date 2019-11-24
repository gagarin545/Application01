package ru.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.application01.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.List;

public class ChartDataAdapter extends ArrayAdapter<BarData> {

    public ChartDataAdapter  (Context context, List<BarData> objects) {
        super(context, 0, objects);
    }
    @NonNull
       @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BarData data = getItem(position);
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()) .inflate(R.layout.list_item_barchat, null);

            holder.chart = convertView.findViewById(R.id.chart);
            convertView.setTag(holder);
        }  else
            holder = (ViewHolder) convertView.getTag();


        ViewHolder finalHolder = holder;
        holder.chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {
                for(String s : finalHolder.chart.getBarData().getDataSetLabels())
                    System.out.println( s);

                System.out.println( e.getData() + "Value: " + e.getY() + ", xIndex: " + e.getX() + ", DataSet index: " + h.getDataSetIndex() + "|" + h.getDataSetIndex()
                + "|" + h.getDataIndex() + ">" + e.getIcon());
            }

            @Override
            public void onNothingSelected() {}
        });

        holder.chart.getDescription().setEnabled(false);
        holder.chart.setDrawGridBackground(false);

        YAxis leftAxis = holder.chart.getAxisLeft();
        leftAxis.setLabelCount(4, false);
        leftAxis.setSpaceTop(20f);

        YAxis rigthAxis = holder.chart.getAxisRight();
        rigthAxis.setLabelCount(4, false);
        rigthAxis.setSpaceTop(20f);

        holder.chart.setData(data);
        holder.chart.setFitBars(true);
        holder.chart.animateY(500);
        return convertView;
    }

    private class ViewHolder{        BarChart chart; }
    }
