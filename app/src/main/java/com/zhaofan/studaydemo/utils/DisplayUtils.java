package com.zhaofan.studaydemo.utils;

import android.content.Context;
import android.util.TypedValue;



/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/10
 * description:
 */
public class DisplayUtils {
    public static void dp2Px(float dp, Context context){
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
}
