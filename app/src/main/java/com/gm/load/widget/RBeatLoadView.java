package com.gm.load.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lgm on 2017/3/1.
 */
public class RBeatLoadView extends View implements Runnable {

    private Paint mPaint = null;

    private float lineOneStartY = 80;
    private float lineOneEndY = 100;
    private float lineSecondStartY = 60;
    private float lineSecondEndY = 120;
    private float lineThirdStartY = 40;
    private float lineThirdEndY = 140;
    private float lineFouthStartY = 60;
    private float lineFouthEndY = 120;
    private float lineFifthStartY = 80;
    private float lineFifthEndY = 100;

    private boolean onePlus = true;
    private boolean twoPlus = true;
    private boolean threePlus = true;
    private RectF rf;
    private RectF rf2;
    private RectF rf3;
    private RectF rf4;
    private RectF rf5;

    public RBeatLoadView(Context context) {
        this(context,null);
    }

    public RBeatLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setAlpha(255);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

        rf = new RectF();
        rf2 = new RectF();
        rf3 = new RectF();
        rf4 = new RectF();
        rf5 = new RectF();

        new Thread(this).start();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rf.set(10, lineOneStartY, 25, lineOneEndY);
        rf2.set(30, lineSecondStartY, 45, lineSecondEndY);
        rf3.set(50, lineThirdStartY, 65, lineThirdEndY);
        rf4.set(70, lineFouthStartY, 85, lineFouthEndY);
        rf5.set(90, lineFifthStartY, 105, lineFifthEndY);

        canvas.drawRoundRect(rf, 7f, 7f, mPaint);
        canvas.drawRoundRect(rf2, 7f, 7f, mPaint);
        canvas.drawRoundRect(rf3, 7f, 7f, mPaint);
        canvas.drawRoundRect(rf4, 7f, 7f, mPaint);
        canvas.drawRoundRect(rf5, 7f, 7f, mPaint);

        if (onePlus){
            lineOneStartY++;
            lineOneEndY--;

            lineFifthEndY--;
            lineFifthStartY++;
        }else {
            lineOneStartY--;
            lineOneEndY++;

            lineFifthEndY++;
            lineFifthStartY--;
        }

        if (twoPlus){
            lineSecondEndY--;
            lineSecondStartY++;
            lineFouthEndY--;
            lineFouthStartY++;
        }else{
            lineSecondEndY++;
            lineSecondStartY--;

            lineFouthEndY++;
            lineFouthStartY--;
        }

        if (threePlus){
            lineThirdStartY++;
            lineThirdEndY--;
        }else{
            lineThirdStartY--;
            lineThirdEndY++;
        }

        if (lineOneStartY >= 60){
            onePlus = false;
        }else if (lineOneStartY <= 20){
            onePlus = true;
        }

        if (lineSecondStartY >= 60){
            twoPlus = false;
        }else if (lineSecondStartY <= 20){
            twoPlus = true;
        }

        if (lineThirdStartY >= 60){
            threePlus = false;
        }else if (lineThirdStartY <= 20){
            threePlus = true;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int measureSpec){
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = getWidth();
            if (mode == MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }

        return result;
    }

    private int measureWidth(int measureSpec){
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY){
            result = size;
        }else{
            result = 200;
            if (mode == MeasureSpec.AT_MOST){
                result = Math.min(result,size);
            }
        }

        return result;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            postInvalidate();
        }
    }

}
