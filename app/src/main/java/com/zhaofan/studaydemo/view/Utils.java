package com.zhaofan.studaydemo.view;

import android.content.res.Resources;
import android.util.TypedValue;

import java.util.Locale;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/17
 * description:
 */
public class Utils {
    public static float dp2Px(float dp){
        return TypedValue.applyDimension(COMPLEX_UNIT_DIP,dp,Resources.getSystem().getDisplayMetrics());
    }
}
