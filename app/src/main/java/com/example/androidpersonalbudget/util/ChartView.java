package com.example.androidpersonalbudget.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.androidpersonalbudget.R;

import java.util.Map;
import java.util.Random;

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

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if(null==data || data.isEmpty())return;

        int maxVal=getMaxValue();
        float widthBar=getWidth()/data.size();
        drawValues(canvas,maxVal,widthBar);
    }

    private void drawValues(Canvas canvas, int maxValue, float widthBar) {
        int currentBarPosition = 0;
        for (String label : data.keySet()) {
            drawValue(canvas, maxValue, widthBar, currentBarPosition, label);
            currentBarPosition++;
        }
    }

    private void drawValue(Canvas canvas, int maxValue, float widthBar, int currentBarPosition, String label) {
        int value = data.get(label);
        int color = generateColor();
        paint.setColor(color);
        drawBar(canvas, maxValue, widthBar, currentBarPosition, value);
        drawLabel(canvas, widthBar, currentBarPosition, label, value);
    }

    private void drawBar(Canvas canvas, int maxValue, float widthBar, int currentBarPosition, int value) {
        float x1 = currentBarPosition * widthBar;
        float y1 = (1 - (float) (value) / maxValue) * getHeight();
        float x2 = x1 + widthBar;
        float y2 = getHeight();
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    private void drawLabel(Canvas canvas, float widthBar, int currentBarPosition, String label, int value) {
        paint.setColor(Color.BLACK);
        paint.setTextSize((float) (0.3 * widthBar));
        float x = (float) (currentBarPosition + 0.5) * widthBar;
        float y = (float) (0.95 * getHeight());
        canvas.rotate(270, x, y);
        canvas.drawText(context.getString(R.string.chart_label, label, value), x, y, paint);
        canvas.rotate(-270, x, y);
    }
    private int generateColor() {
        Random random=new Random();

        return Color.argb(100, 1 + random.nextInt(254),
                1 + random.nextInt(254),
                1 + random.nextInt(254));
    }

    private int getMaxValue() {
        int maxVal = 0;
        for (Integer value : data.values()) maxVal=Math.max(maxVal,value);

        return maxVal;
    }
}
