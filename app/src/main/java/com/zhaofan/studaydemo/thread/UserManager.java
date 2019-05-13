package com.zhaofan.studaydemo.thread;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/6
 * description:
 */
public class UserManager {
    private final Object molitors = new Object();
    User user;
    UserManager(User user){
        this.user = user;
    }


    public synchronized void updateBy(){

    }
}
