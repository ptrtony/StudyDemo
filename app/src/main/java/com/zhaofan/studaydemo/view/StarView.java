package com.zhaofan.studaydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/17
 * description:
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class StarView extends View {
    private static final String TAG = "StarView";
    Path path = new Path();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int ANGLE = 30;
    private int LENGTH = (int) Utils.dp2Px(200);
    private int RADIUS = (int) Utils.dp2Px(80);
    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    {
        paint.setStrokeWidth(Utils.dp2Px(4));
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        path.addArc(200,200,400,400,-255,255);
        path.arcTo(400,200,600,400,-180,225,false);
        path.lineTo(400,542);
        path.close();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        path.moveTo(getWidth()/2,getHeight()/2-RADIUS);
        Log.d(TAG,"Math.cos(Math.toRadians(ANGLE))"+Math.cos(Math.toRadians(ANGLE)));
        Log.d(TAG,"Math.sin(45):"+Math.sin(Math.toRadians(ANGLE))+"\n"
                +"Math.cos(Math.toRadians(ANGLE)):"+ Math.cos(Math.toRadians(ANGLE)));
        canvas.drawPath(path,paint);

    }
}
