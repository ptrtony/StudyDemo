package com.zhaofan.studaydemo.enviroment;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class ConcreteState2 extends State {
    @Override
    public void handle1() {
        super.context.setCurrentStates(Context.STATE1);
        super.context.handle2();
    }

    @Override
    public void handle2() {

    }
}
