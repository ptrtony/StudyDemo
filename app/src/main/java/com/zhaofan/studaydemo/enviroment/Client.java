package com.zhaofan.studaydemo.enviroment;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class Client {
    public static void main(String[] args){
        Context context = new Context();
        context.setCurrentStates(new ConcreteState1());
        context.handle1();
        context.handle2();
    }
}
