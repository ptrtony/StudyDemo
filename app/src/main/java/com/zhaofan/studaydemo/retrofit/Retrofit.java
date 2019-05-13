package com.zhaofan.studaydemo.retrofit;



import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/5
 * description:
 */
public class Retrofit {

    private static OkHttpClient mOkhttpClient;
    public Retrofit(OkHttpClient mOkhttpClient){
        this.mOkhttpClient = mOkhttpClient;
    }

    public static<T> T createService(Class<T> service){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        final Annotation[] annotations = method.getAnnotations();
                        for (int i=0;i<annotations.length;i++){
                            if (annotations[i] instanceof GET){
                                final GET annotation = (GET) annotations[i];
                                return parseGet(annotation.value(),method,args);
                            }else if (annotations[i] instanceof POST){
                                final POST annotation = (POST) annotations[i];
                                final String url = annotation.value();

                            }
                        }
                        return null;
                    }
                });
    }


    private static Call parseGet(String url, Method method, Object[] args){
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        return mOkhttpClient.newCall(request);
    }

}
