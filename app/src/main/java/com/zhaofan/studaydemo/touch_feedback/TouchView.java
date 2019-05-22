package com.zhaofan.studaydemo.touch_feedback;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/22
 * description:
 */
public class TouchView extends View {
    Bitmap bitmap;
    private int offsetX,offsetY;
    private int bitmapWidth;
    private int bitmapHeight;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int IMAGE_WIDTH = (int) Utils.dp2px(150);
    private float fraction;
    private ObjectAnimator translateAnimator;
    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Utils.getAvator(getResources(),IMAGE_WIDTH);
    }

    private ObjectAnimator getTrainslateAnimator(){
        if (translateAnimator == null){
            translateAnimator = ObjectAnimator.ofFloat(this,"fraction",1);
        }
        translateAnimator.setIntValues(bitmapWidth,bitmapHeight);
        translateAnimator.setDuration(10000);
        return translateAnimator;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        offsetX = (w - bitmap.getWidth())/2;
        offsetY = (h-bitmap.getHeight())/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,offsetX,offsetY,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int startX = 0;
        int startY = 0;
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                startY = (int) event.getY();
                getTrainslateAnimator().start();
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = (int) (event.getX() - startX)-bitmap.getWidth()/2;
                offsetY = (int) (event.getY() - startY)-bitmap.getHeight()/2;
                bitmapWidth = (int) event.getX();
                bitmapHeight = (int) event.getX();
                invalidate();
                break;
        }
        return true;
    }
}
