package com.zhaofan.studaydemo.game;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class GamePlayIH implements InvocationHandler {
    private static final String TAG = "gameplay";
    //被代理者
    Class cls = null;
    //被代理的实例
    Object object = null;
    public GamePlayIH(Object _object){
        this.object = _object;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.object,args);
        if (method.getName().equalsIgnoreCase("login")){
            Log.d(TAG,"有人在用我的账号登录");
        }
        return result;
    }
}
