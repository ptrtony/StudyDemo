package com.zhaofan.studaydemo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhaofan.studaydemo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TimeDownActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mVerifyCodeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_down);
        mVerifyCodeBtn = findViewById(R.id.btn_code_verify);
        mVerifyCodeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }


    public void rxjavaRepeatWhenObservable(){
        Observable.range(1,30)
                .repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                        return Observable.timer(10,TimeUnit.SECONDS);
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
