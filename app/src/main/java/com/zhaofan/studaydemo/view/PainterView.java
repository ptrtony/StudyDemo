package com.zhaofan.studaydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/17
 * description:
 */
public class PainterView extends View {
    Path path = new Path();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    SparseArray<Path> paths = new SparseArray<>();
    public PainterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setColor(Color.parseColor("#000000"));
//        paint.setTextSize(Utils.dp2Px(12));
        paint.setStrokeWidth(Utils.dp2Px(4));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float startX = 0;
        float startY = 0;

        switch(event.getAction()){
//            case MotionEvent.ACTION_POINTER_DOWN:
//                int index = event.getActionIndex();
//                int pointerId =event.getPointerId(index);
//                break;

//            case MotionEvent.ACTION_POINTER_UP:
//                break;
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                path.moveTo(startX,startY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceX = event.getX()-startX;
                float distanceY = event.getY() - startY;
                path.lineTo(distanceX,distanceY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                path.reset();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        for (int i=0;i<paths.size();i++){
//            canvas.drawPath(paths.get(i),paint);
//        }
        canvas.drawPath(path,paint);
    }


    public void clearPath(){
        path.reset();
        invalidate();
    }
}
