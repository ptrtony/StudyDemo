package com.zhaofan.studaydemo.factory.product;

import java.lang.reflect.Constructor;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class SingletonFactory {
    private  static Singleton singleton;

    static{
        try {
            Class cl = Class.forName(Singleton.class.getName());
            Constructor constructor = cl.getConstructor();
            constructor.setAccessible(true);
            singleton = (Singleton) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Singleton getSingleton(){
        return singleton;
    }
}
