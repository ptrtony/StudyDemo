package com.zhaofan.studaydemo.ruler;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/22
 * description:
 */
public class DisplayUtil {
    public static int dip2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,Resources.getSystem().getDisplayMetrics());
    }


    public static float dp2Sp(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,dp,Resources.getSystem().getDisplayMetrics());
    }
}
