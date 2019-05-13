package com.zhaofan.studaydemo.rxjava;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding3.view.RxView;
import com.zhaofan.studaydemo.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;

public class RxjavaJustActivity extends AppCompatActivity {
    TextView mTvReceive;
    EditText mEtnSend;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_save_store);
        mEtnSend = findViewById(R.id.etn_send_data);
        mTvReceive = findViewById(R.id.tv_receive_data);
        button = findViewById(R.id.btn_click);

        RxView.clicks(button)
                .subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit) throws Exception {

                    }
                });


    }

    public void onSendObservableClick(View view){
        String sendStr = mEtnSend.getText().toString().trim();
        if (!TextUtils.isEmpty(sendStr)){
            sendData(sendStr);
        }

    }


    private void sendData( String sendDataStr){

//        Observable.just(sendDataStr)
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) throws Exception {
//                        return s.toLowerCase();
//                    }
//                })
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) throws Exception {
//                        return s + " world";
//                    }
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        mTvReceive.setText(s);
//                    }
//                });



        User user = new User();
        user.addresses = new ArrayList<>();
        user.userName = "tony";
        User.Address address1 = new User.Address();
        address1.street = "ren ming road";
        address1.city = "Su Zhou";
        user.addresses.add(address1);

        User.Address address2 = new User.Address();
        address2.street = "dong wu bei road";
        address2.city = "Su Zhou";
        user.addresses.add(address2);

        Observable.just(user)
                .flatMap(new Function<User, ObservableSource<User.Address>>() {
                    @Override
                    public ObservableSource<User.Address> apply(User user) throws Exception {
                        return Observable.fromIterable(user.addresses);
                    }
                })
                .subscribe(new Consumer<User.Address>() {
                    @Override
                    public void accept(User.Address address) throws Exception {

                    }
                });

//        Observable.just(user)
//                .map(new Function<User, List<User.Address>>() {
//                    @Override
//                    public List<User.Address> apply(User user) throws Exception {
//
//                        return user.addresses;
//                    }
//                })
//
//                .subscribe(new Consumer<List<User.Address>>() {
//                    @Override
//                    public void accept(List<User.Address> addresses) throws Exception {
//                        for (User.Address address:addresses){
//                            mTvReceive.setText(address.street+"\n");
//                        }
//                    }
//                });


    }


    private void observableRang(){
        Observable.range(1,8)
                .groupBy(new Function<Integer, Map<String,Integer>>() {
                    @Override
                    public Map<String,Integer> apply(Integer integer) throws Exception {

                        Map<String,Integer> maps = new HashMap<>();
                        if (integer%2==0){
                            maps.put("偶数",integer);
                        }else{
                            maps.put("基数",integer);
                        }
                        return maps;
                    }
                }).subscribe(new Consumer<GroupedObservable<Map<String, Integer>, Integer>>() {
            @Override
            public void accept(GroupedObservable<Map<String, Integer>, Integer> mapIntegerGroupedObservable) throws Exception {
                Map<String,Integer> map = mapIntegerGroupedObservable.getKey();
                Set set = map.entrySet();
                Map.Entry<String,Integer> entry = (Map.Entry) set.iterator();
                List<Integer> list = new ArrayList<>();
                if (entry.getKey().equals("偶数")){
                    list.add(entry.getValue());
                }
            }
        });

    }


    private void setObservableElementAt(){
        Observable.just(1,2,3,4,5,6,6)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }


    private void setObservableFilter(){
        Observable.just(2,30,22,5,69,1)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer>10;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }


    private void getDebounce(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws InterruptedException {
                if (emitter.isDisposed())return;
                for (int i=1;i<=10;i++){
                    emitter.onNext(i);//发射数据
                    Thread.sleep(i*100);
                }
            }
        }).debounce(500,TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }

    private void  defalutIfEmpty(){
        Observable.empty()
                .switchIfEmpty(Observable.just(1,2,3))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) {

                    }
                });
    }

    //调度的发射物小于某个条件变为false
    private void skipWhile(){
        Observable.just(1,2,3,4,5,6)
                .skipWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer<=2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {

                    }
                });

    }

    private void getOddAndEvents(){
        Observable<Integer> odds = Observable.just(1,3,5,6,7,8,9);
        Observable<Integer> events = Observable.just(2,4,6);

        Observable.zip(odds, events, new BiFunction<Integer, Integer, Object>() {
            @Override
            public Object apply(Integer integer, Integer integer2) {
                return integer+integer2;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });
    }

    private void joinObservable() throws InterruptedException {
        Observable<Integer> o1 = Observable.just(1,2,3);
        Observable<Integer> o2 = Observable.just(4,5,6);

        o1.just(o2, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(String.valueOf(integer)).delay(200, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Integer,Integer,String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                return integer+":"+integer2;
            }
        });

        Thread.sleep(2000);
    }


    private void getWithArray(){
        Observable.just("Hello Java","Hello Kotlin","Hello Sa")
                .startWithArray("Hello Groovy","Hello Clojure")
                .startWith("Hello Rx")
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("onNext:"+s);
                    }
                });
    }






}
