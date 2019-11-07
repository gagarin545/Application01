package ru.util;

import android.graphics.Color;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ru.entity.Result;
import ru.entity.WorkerResult;

public class GenerateData {
    private ArrayList<BarEntry> order_blue = new ArrayList<>();
    private ArrayList<BarEntry> order_red = new ArrayList<>();
    private ArrayList<String> titl;
    private String names = null;
    private BarDataSet dataset;
    private BarData data;
    public BarData getBarData(List<WorkerResult> results, long timeclose){
        titl = new ArrayList<>();
        int i = 0;
        for(WorkerResult result: results)
            if (result.getTimeclose() == timeclose) {
                titl.add(result.getName());
                order_blue.add(new BarEntry(i++, (float) result.getCount(), result.getName()));
            }
        dataset = new BarDataSet(order_blue,   " заявки на " + new Date(timeclose));
        dataset.setColors( Color.GREEN);
        dataset.setBarShadowColor( Color.rgb(203,203,203));

        data = new BarData( dataset);
        data.setValueFormatter(new MyXAxisValueFormatter( titl));

        data.setBarWidth(0.4f);
        if( titl.size() == 0) data = null;
        return data;
    }

    public BarData getBarData(List<Result> results, int iddivision){
        titl = new ArrayList<>();
        int i = 0, kcount = 0, scount = 0;

        for(Result result: results) {
            if( result.getIddivision() == iddivision) {
                names= result.getNamedivision();
               // Log.e(Debug, result.getIddivision() + " | " + new Date(result.getTimeclose()) + " | " + result.getKvcount() + " | " + result.getSkvcount());
                titl.add(String.valueOf(new Date(result.getTimeclose())));
                order_blue.add(new BarEntry(i, (float) result.getKvcount(), new Date(result.getTimeclose())));
                kcount += result.getKvcount();
                order_red.add(new BarEntry((0.3f + i++), (float) result.getSkvcount(), ""));
                scount += result.getSkvcount();
            }
        }
        dataset = new BarDataSet(order_blue,   " заявок - " + kcount );
        BarDataSet dataset2 = new BarDataSet(order_red, "  Просрочено - " + scount  + " " + names);
        dataset.setColors(Color.GREEN);
        dataset2.setColors(Color.RED);
        dataset2.setBarShadowColor(Color.rgb(203,203,203));
        dataset.setBarShadowColor(Color.rgb(203,203,203));
        data = new BarData(dataset, dataset2);
        data.setValueFormatter(new MyXAxisValueFormatter( titl));
        data.setBarWidth(0.4f);
        if( names == null) data = null;
        return data;
    }

}
