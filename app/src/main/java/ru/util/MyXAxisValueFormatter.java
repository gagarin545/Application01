package ru.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class MyXAxisValueFormatter implements IAxisValueFormatter, IValueFormatter {
    private ArrayList mValues;
    MyXAxisValueFormatter(ArrayList mValues) {        this.mValues = mValues;    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return String.valueOf(mValues.get((int) value));    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return String.valueOf(entry.getData());
    }
}