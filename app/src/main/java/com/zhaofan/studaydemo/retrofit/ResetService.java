package com.zhaofan.studaydemo.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/5
 * description:
 */
public class ResetService {
    private static OkHttpClient okHttpClient;

    public static void init(){
        okHttpClient = new OkHttpClient.Builder().build();
    }


    public static<T> void todayGank(Class<T> clazz,NetCallback<T> callback){
        Request request = new Request
                .Builder()
                .url("http://gank.io/api/today")
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new WrapperOkhttpCallback(clazz,callback));
    }


    public static<T> void xiaofeiGank(Class<T> clazz,NetCallback<T> callback){
        Request request = new Request.Builder()
                .get()
                .url("http://gank.io/api/xiandu/data/id/appinn/count/\" + count + \"/page/\" + page")
                .build();

        okHttpClient.newCall(request).enqueue(new WrapperOkhttpCallback(clazz,callback));
    }



}
