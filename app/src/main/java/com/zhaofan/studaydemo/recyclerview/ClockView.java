//package com.zhaofan.studaydemo.recyclerview;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.zhaofan.studaydemo.R;
//
//
//import androidx.annotation.Nullable;
//
///**
// * @author ChengJingQiang
// * @copyright:2019
// * @project NettyChat
// * @date 2019/5/6
// * description:
// */
//public class ClockView extends View {
//    //view的默认大小
//    private static final int DEFALUT_SIZE = 320;
//    private int roundStockWidth ;
//    private Paint mClockPaint;
//    private Paint mTextPaint;
//    private int strockColor;
//    private int textSize;
//    private int clockCenterColor;
//    private int hour;
//    private int minute;
//    private int second;
//    public ClockView(Context context) {
//        this(context,null);
//    }
//
//    public ClockView(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs,0);
//    }
//
//    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ClockView);
//        roundStockWidth = (int) typedArray.getDimension(R.styleable.ClockView_strockWidth,dp2px(10));
//        strockColor = typedArray.getColor(R.styleable.ClockView_strockColor,Color.BLUE);
//        clockCenterColor = typedArray.getColor(R.styleable.ClockView_clockCenterColor,Color.RED);
//        textSize = typedArray.getDimensionPixelSize(R.styleable.ClockView_textSize,dp2px(13));
//
//
//        initClockPaint();
//        initTextPaint();
//
//
//        typedArray.recycle();
//
//
//    }
//
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int defaultSize = dp2px(DEFALUT_SIZE);
//        int widthSize = getSize(widthMeasureSpec,defaultSize);
//        int heightSize = getSize(heightMeasureSpec,defaultSize);
//        int size = Math.min(widthSize,heightSize);
//        setMeasuredDimension(size,size);
//    }
//
//    private void initClockPaint(){
//        mClockPaint = new Paint();
//        mClockPaint.setAntiAlias(true);
//        mClockPaint.setStyle(Paint.Style.STROKE);
//        mClockPaint.setColor(strockColor);
//        mClockPaint.setStrokeWidth(roundStockWidth);
//    }
//
//
//    private void initTextPaint(){
//        mTextPaint = new Paint();
//        mTextPaint.setColor(Color.BLACK);
//        mTextPaint.setStyle(Paint.Style.FILL);
//        mTextPaint.setTextSize(textSize);
//        mTextPaint.setAntiAlias(true);
//        mTextPaint.setTextAlign(Paint.Align.CENTER);
//    }
//
//
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //中心点的横轴坐标
//        int pointWH = getWidth()/2;
//        //内圆的半径
//        float radius = pointWH - roundStockWidth;
//
//        canvas.translate(pointWH,pointWH);
//        if (roundStockWidth>0){
//            canvas.drawCircle(0,0,pointWH-roundStockWidth/2,mClockPaint);
//        }
//        mClockPaint.setColor(Color.WHITE);
//        mClockPaint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(0,0,radius,mClockPaint);
//
//        //绘制小短线
//        canvas.save();
//        canvas.rotate(-90);
//        float lengthLine = radius/16.0f;
//        float startY = radius - lengthLine;
//        float stopY = startY - lengthLine;
//        float stockWidth = 2;
//        float temp = lengthLine / 4.0f;
//        float shortStartY = startY - temp;
//        float shortStopY = stopY + temp;
//        float shortStockWidth = stockWidth / 2.0f;
//        mClockPaint.setColor(Color.BLACK);
//        float degrees = 6;
//        for (int i = 0; i <= 360; i += degrees) {
//            if (i % 30 == 0) {
//                mClockPaint.setStrokeWidth(stockWidth);
//                canvas.drawLine(0, startY, 0, stopY,mClockPaint);
//            } else {
//                mClockPaint.setStrokeWidth(shortStockWidth);
//                canvas.drawLine(0, shortStartY, 0, shortStopY, mClockPaint);
//            }
//            canvas.rotate(degrees);
//        }
//        canvas.restore();
//
//        //绘制时钟数字
//        if (textSize > 0) {
//            float x, y;
//            for (int i = 1; i <= 12; i += 1) {
//                mTextPaint.getTextBounds(String.valueOf(i), 0, String.valueOf(i).length(), rect);
//                float textHeight = rect.height();
//                float distance = radius - 2 * lengthLine - textHeight;
//                double tempVa = i * 30.0f * Math.PI / 180.0f;
//                x = (float) (distance * Math.sin(tempVa));
//                y = (float) (-distance * Math.cos(tempVa));
//                canvas.drawText(String.valueOf(i), x, y + textHeight / 3, mTextPaint);
//            }
//        }
//
//        canvas.rotate(-90);
//
//        mClockPaint.setStrokeWidth(2);
//        //绘制时针
//        canvas.save();
//        canvas.rotate(hour / 12.0f * 360.0f);
//        canvas.drawLine(-30, 0, radius / 2.0f, 0, mClockPaint);
//        canvas.restore();
//        //绘制分针
//        canvas.save();
//        canvas.rotate(minute / 60.0f * 360.0f);
//        canvas.drawLine(-30, 0, radius * 0.7f, 0, mClockPaint);
//        canvas.restore();
//        //绘制秒针
//        mClockPaint.setColor(Color.parseColor("#fff2204d"));
//        canvas.save();
//        canvas.rotate(second / 60.0f * 360.0f);
//        canvas.drawLine(-30, 0, radius * 0.85f, 0, mClockPaint);
//        canvas.restore();
//        //绘制中心小圆点
//        mClockPaint.setStyle(Paint.Style.FILL);
//        mClockPaint.setColor(clockCenterColor);
//        canvas.drawCircle(0, 0, radius / 20.0f, mClockPaint);
//    }
//
//    private int getSize(int measureSpec, int defaultSize){
//        int size = 0;
//        int mode = MeasureSpec.getMode(measureSpec);
//        switch (mode){
//            case MeasureSpec.AT_MOST:
//                size = Math.min(MeasureSpec.getSize(measureSpec),defaultSize);
//                break;
//            case MeasureSpec.EXACTLY:
//                size = MeasureSpec.getSize(defaultSize);
//                break;
//            case MeasureSpec.UNSPECIFIED:
//                size = defaultSize;
//                break;
//        }
//        return size;
//    }
//
//
//    private int dp2px(int dpSize){
//        float scale = getResources().getDisplayMetrics().density;
//        return (int) (scale*dpSize+0.5f);
//    }
//}
