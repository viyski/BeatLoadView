package com.gm.load.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.gm.load.R;

/**
 * Created by lgm on 2017/1/11.
 */
public class BeatLoadView extends View {

    private Context mContext;
    private Paint mPaint;
    private int mPaintColor;
    private float mStrokeWidth;
    private float mHeight;
    private float mPadding;
    private boolean running = true;

    private float lineOneStartY, lineOneEndY;
    private float lineSecondStartY, lineSecondEndY;
    private float lineThirdStartY, lineThirdEndY;
    private float paddingOne, paddingSecond, paddingThird;
    private boolean onePlus;
    private boolean secondPlus;
    private boolean thirdPlus;

    private Thread mThread;

    public BeatLoadView(Context context) {
        this(context, null);
    }

    public BeatLoadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BeatLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BeatLoadView);
        mPaintColor = typedArray.getColor(R.styleable.BeatLoadView_paintColor, Color.GRAY);
        mStrokeWidth = typedArray.getDimension(R.styleable.BeatLoadView_strokeWidth, dp2px(5));
        mHeight = typedArray.getDimension(R.styleable.BeatLoadView_itemHeight, dp2px(20));
        mPadding = typedArray.getDimension(R.styleable.BeatLoadView_itemsPadding, dp2px(7));
        typedArray.recycle();
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(mPaintColor);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);

        paddingOne = mPadding;
        paddingSecond = mPadding * 2;
        paddingThird = mPadding * 3;
        reset();

        mThread = new Thread() {
            @Override
            public void run() {
                while (true) {

                    if (running)
                        postInvalidate();

                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        mThread.start();
    }

    private void reset() {
        lineOneStartY = mHeight / 1.5f;
        lineOneEndY = mHeight;
        lineSecondStartY = mHeight / 3;
        lineSecondEndY = mHeight;
        lineThirdStartY = mHeight / 6;
        lineThirdEndY = mHeight;
        onePlus = true;
        secondPlus = true;
        thirdPlus = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!running) {
            reset();
        }

        canvas.drawLine(paddingOne, lineOneStartY, paddingOne, lineOneEndY, mPaint);
        canvas.drawLine(paddingSecond, lineSecondStartY, paddingSecond, lineSecondEndY, mPaint);
        canvas.drawLine(paddingThird, lineThirdStartY, paddingThird, lineThirdEndY, mPaint);

        if (onePlus) {
            lineOneStartY++;
        } else {
            lineOneStartY--;
        }
        if (lineOneStartY >= mHeight) {
            onePlus = false;
        } else if (lineOneStartY <= 0) {
            onePlus = true;
        }

        if (secondPlus) {
            lineSecondStartY++;
        } else {
            lineSecondStartY--;
        }

        if (lineSecondStartY >= mHeight) {
            secondPlus = false;
        } else if (lineSecondStartY <= 0) {
            secondPlus = true;
        }

        if (thirdPlus) {
            lineThirdStartY++;
        } else {
            lineThirdStartY--;
        }
        if (lineThirdStartY >= mHeight) {
            thirdPlus = false;
        } else if (lineThirdStartY <= 0) {
            thirdPlus = true;
        }

    }

    public boolean isRunning() {
        return running;
    }

    public void setDrawRunning(boolean running) {
        this.running = running;
    }

    private float dp2px(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return displayMetrics.density * dp;
    }

}
