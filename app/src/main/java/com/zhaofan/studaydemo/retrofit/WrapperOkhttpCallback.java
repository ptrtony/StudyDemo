package com.zhaofan.studaydemo.retrofit;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/5
 * description:
 */
public class WrapperOkhttpCallback<T> implements Callback {
    private static final String TAG = WrapperOkhttpCallback.class.getSimpleName();
    private static Gson gson = new Gson();
    private Class<T> clazz;
    private NetCallback<T> callback;

    public WrapperOkhttpCallback(Class<T> responseClazz,NetCallback<T> netCallback){
        this.clazz = responseClazz;
        this.callback = netCallback;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e("WrapperOkHttpCallback", "onFailure");
        e.printStackTrace();
        callback.onFailure(e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(response.body().charStream());
        T entity = gson.getAdapter(clazz).read(jsonReader);
        Log.d(TAG,entity.toString());
        callback.onSuccess(entity);
    }
}
