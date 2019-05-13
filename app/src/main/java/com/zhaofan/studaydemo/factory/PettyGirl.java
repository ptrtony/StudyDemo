package com.zhaofan.studaydemo.factory;

import android.util.Log;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/11
 * description:
 */
public class PettyGirl implements IGoodBodyGirl,IGreatTemperamenetGirl{
    private static final String TAG = "PettyGirl";
    protected String name;
    public PettyGirl(String name){
        this.name = name;
    }
    @Override
    public void goodLooking() {
        Log.d(TAG,this.name+"-------脸蛋很漂亮");
    }

    @Override
    public void niceFigure() {
        Log.d(TAG,this.name+"-------气质非常好！");
    }

    @Override
    public void greatTemperament() {
        Log.d(TAG,this.name+"-------身材非常棒！");
    }
}
