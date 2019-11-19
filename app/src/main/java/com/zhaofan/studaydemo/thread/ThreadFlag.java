package com.zhaofan.studaydemo.thread;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019-11-19
 * description:
 */

//java程序中，如何安全的结束一个正在运行的线程？
    //方式一 使用共享变量的方式
public class ThreadFlag extends Thread{
    public volatile boolean exit = false;

    @Override
    public void run() {
        while (!exit);
    }

    public static void main(String[] agrs){
        ThreadFlag thread = new ThreadFlag();
        thread.run();
        try {
            Thread.sleep(1000);
            thread.exit = true;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}



