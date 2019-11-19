package com.zhaofan.studaydemo.thread;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019-11-19
 * description:
 */
public class InterruptThread {
    public static void main(String[] agrs) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        myThread.stop = true;// 设置共享变量为true
        myThread.interrupt();// 阻塞时退出阻塞状态
    }
    public static class MyThread extends Thread{
        public volatile boolean stop = false;
        @Override
        public void run() {
            super.run();
            while (!stop){
                System.out.println(getName()+"正在执行线程...");
                try{
                    Thread.sleep(1000);
                }catch (Exception e) {
                    System.out.println("抛出异常,终止线程执行...");
                    stop = true;
                }
            }
            System.out.println("结束线程");
        }
    }
}


