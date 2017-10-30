package com.aquamorph.habquit.fragments;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
/**
 * Created by Josh Derr on 3/8/2017.
 */

public class StringDayAxisValueFormatter implements IAxisValueFormatter {

    private String[] mValues;

    public StringDayAxisValueFormatter(String[] values) {
        this.mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        return mValues[((int) value)-1];
    }

}
