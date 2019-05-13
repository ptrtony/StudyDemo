package com.zhaofan.studaydemo.factory;

import android.util.Log;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/11
 * description:
 */
public class Searcher extends AbstractSearch{
    private static final String TAG = "Searcher";

    public Searcher(IGreatTemperamenetGirl iGreatTemperamenetGirl) {
        super(iGreatTemperamenetGirl);
    }

    public Searcher(IGoodBodyGirl iGoodBodyGirl) {
        super(iGoodBodyGirl);
    }


    @Override
    void show() {
        Log.d(TAG,"---------------美女的信息如下：--------------");
        //展示面容
        super.iGoodBodyGirl.goodLooking();
        super.iGreatTemperamenetGirl.greatTemperament();
        super.iGoodBodyGirl.niceFigure();
    }
}
