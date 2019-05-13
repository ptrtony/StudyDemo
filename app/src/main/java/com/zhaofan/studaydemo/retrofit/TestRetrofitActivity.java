package com.zhaofan.studaydemo.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.zhaofan.studaydemo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class TestRetrofitActivity extends AppCompatActivity {
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        gson = new Gson();
    }


    public void onGETClick(View view){
        getTodayGankByRetrofit();
    }


    private void getTodayGankByRetrofit(){
        Retrofit retrofit = new Retrofit(new OkHttpClient());
        retrofit.createService(NetRestService.class).todayGank().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                JsonReader jsonReader = gson.newJsonReader(response.body().charStream());
                TodayEntity entity = gson.getAdapter(TodayEntity.class).read(jsonReader);
                Log.d("TestRetrofitActivity","调用成功，成功的结果为："+entity.toString());
            }
        });
    }
}
