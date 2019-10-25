package com.example.wero.a1_20154816395.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * @Title: ${filename}
 * @user: wero
 * @date: 18-5-24
 */

public class CustomXAxisValueFormatter implements IAxisValueFormatter {
    List<String> date;
    public CustomXAxisValueFormatter(List<String> list){
        date = list;
    }
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if (date.size() > (int)value)
            return date.get((int)value).substring(5);
        else
            return "";
    }
}
