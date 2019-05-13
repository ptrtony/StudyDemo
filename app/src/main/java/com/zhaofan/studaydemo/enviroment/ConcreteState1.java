package com.zhaofan.studaydemo.enviroment;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class ConcreteState1 extends State{
    @Override
    public void handle1() {

    }

    @Override
    public void handle2() {
        super.context.setCurrentStates(Context.STATE2);
        super.context.handle2();
    }
}
