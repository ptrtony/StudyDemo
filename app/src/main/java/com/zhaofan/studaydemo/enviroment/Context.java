package com.zhaofan.studaydemo.enviroment;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class Context {
    public static final State STATE1 = new ConcreteState1();
    public static final State STATE2 = new ConcreteState2();

    private State currentStates;

    public State getCurrentStates(){
        return currentStates;
    }

    public void setCurrentStates(State currentStates){
        this.currentStates = currentStates;
        this.currentStates.setContext(this);
    }

    public void handle1(){
        this.currentStates.handle1();
    }

    public void handle2(){
        this.currentStates.handle2();
    }

}
