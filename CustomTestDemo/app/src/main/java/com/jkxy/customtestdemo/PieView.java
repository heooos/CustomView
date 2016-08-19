package com.jkxy.customtestdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by zh on 16/8/18.
 */
public class PieView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    // 饼状图初始绘制角度
    private float mStartAngle = 0;
    // 数据
    private ArrayList<DataInfo> mData;
    // 宽高
    private int mWidth, mHeight;
    // 画笔
    private Paint mPaint = new Paint();
    private Paint mPaintStroke = new Paint();

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public PieView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("onDraw","onDraw");
        if (null == mData)
            return;
        float currentStartAngle = mStartAngle;                    // 当前起始角度
        canvas.translate(mWidth / 2, mHeight / 2);                // 将画布坐标原点移动到中心位置
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);  // 饼状图半径
        RectF rect = new RectF(-r, -r, r, r);                     // 饼状图绘制区域

        for (int i = 0; i < mData.size(); i++) {
            DataInfo pie = mData.get(i);
            mPaint.setColor(pie.getColor());
            mPaintStroke.setColor(Color.BLACK);
            canvas.drawArc(rect, currentStartAngle, pie.getAngle(), true, mPaint);
            canvas.drawArc(rect, currentStartAngle,pie.getAngle(),true,mPaintStroke);
            currentStartAngle += pie.getAngle();
        }
    }

    public void setStartAngle(int angle){
        this.mStartAngle = angle;
        invalidate();   // 刷新
    }

    public void setData(ArrayList<DataInfo> mData){
        this.mData = mData;
        initDate(mData);
        mPaint.setStyle(Paint.Style.FILL);
        mPaintStroke.setStyle(Paint.Style.STROKE);
        invalidate();   // 刷新
    }

    public void initDate(ArrayList<DataInfo> mData){
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            DataInfo pie = mData.get(i);

            sumValue += pie.getValue();       //计算数值和
            Log.d("value", pie.getValue()+"");
            int j = mColors.length % (i+1) + 1;       //设置颜色
            Log.d("color",j+"");
            pie.setColor(mColors[j]);
        }
        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            DataInfo pie = mData.get(i);


            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度
            Log.d("percentage", pie.getPercentage()+"");
            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;

            Log.i("angle", "" + pie.getAngle());
        }
    }

}
