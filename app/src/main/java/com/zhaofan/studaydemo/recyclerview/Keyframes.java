package com.zhaofan.studaydemo.recyclerview;

import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/22
 * description:
 */
public class Keyframes {
    private float[] mX;
    private float[] mY;
    private float[] mAngle;

    private void initPath(Path path){
        final PathMeasure pathMeasure = new PathMeasure(path,false);
    }
}
