package com.zhaofan.studaydemo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding3.widget.RxTextView;
import com.zhaofan.studaydemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * rxjava 中的debound实例  原理 过滤掉由observable发送过快的数据，如果在一段时间内没有发送数据，就发射最后一个数据
 */
public class RxDeboundActivity extends AppCompatActivity {
    private static final String TAG = "RxDeboundActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_debound);
        EditText editText = findViewById(R.id.etn_send_view);


        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("RxDeboundActivity",">>>>>>>>"+integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        io.reactivex.Observable a = io.reactivex.Observable.just(12,23,34,12,32,1,2);
        io.reactivex.Observable b = io.reactivex.Observable.just(23,12,23,2,3);

        io.reactivex.Observable.concat(a, b)
                .subscribe(observer);

        RxTextView.textChanges(editText)
                .debounce(5000,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        Log.d(TAG,charSequence.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
