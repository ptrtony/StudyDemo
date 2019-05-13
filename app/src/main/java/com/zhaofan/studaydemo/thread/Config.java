package com.zhaofan.studaydemo.thread;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/6
 * description:
 */
public class Config {
    private static Config instance;

    public synchronized static Config getInstance(){
        if (instance == null){
            instance = new Config();
        }
        return instance;
    }


    public static Config getInstance1(){
        if (instance == null){
            synchronized (Config.class){
                if (instance == null){
                    instance = new Config();
                }
            }
        }
        return instance;
    }
}
