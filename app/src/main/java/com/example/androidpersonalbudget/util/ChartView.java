package com.example.androidpersonalbudget.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Map;

public class ChartView extends View {
    private Context context;
    private Map<String, Integer> data;
    private Paint paint;
    public ChartView(Context context, Map<String, Integer> data) {
        super(context);
        this.context = context;
        this.data = data;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
    }
}
