package com.zhaofan.studaydemo.rxjava

import android.annotation.SuppressLint
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe


/**
 *@copyright:2019
 *@project NettyChat
 *@author ChengJingQiang
 *@date 2019/5/15
 *description:
 */
class RxjavaToHelloWorld {
    @SuppressLint("CheckResult")
    fun printHelloWorld(){
        //创建被观察者
        Observable.create(ObservableOnSubscribe<String> { emitter ->
             emitter.onNext("Hello World")
        }).subscribe()

    }
}