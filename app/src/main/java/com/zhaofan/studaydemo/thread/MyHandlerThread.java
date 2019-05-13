package com.zhaofan.studaydemo.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/25
 * description:
 */
public class MyHandlerThread extends HandlerThread {
    private Handler mHandler;
    public MyHandlerThread(String name) {
        super(name);
    }

    public MyHandlerThread(String name, int priority) {
        super(name, priority);
    }


    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        //Handle message
                        break;
                    case 2:
                        //Handle message
                        break;
                }
            }
        };
    }

    public void handlerMessage1(){
        mHandler.sendEmptyMessage(1);
    }


    public void handlerMessage2(){
        mHandler.sendEmptyMessage(2);
    }
}
