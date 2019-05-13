package com.zhaofan.studaydemo.factory.car;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public abstract class CarModel {
    private ArrayList<String> sequence = new ArrayList<>();
    protected abstract void start();
    protected abstract void alarm();
    protected  abstract void stop();
    protected abstract void engineBoom();



    final public void run(){
        for (int i=0;i<this.sequence.size();i++){
            String actionName = this.sequence.get(i);
            if (actionName.equalsIgnoreCase("start")){
                this.start();
            }else if (actionName.equalsIgnoreCase("stop")){
                this.stop();
            }else if (actionName.equalsIgnoreCase("alarm")){
                this.alarm();
            }else{
                this.engineBoom();
            }
        }
    }


    final public void setSequence(ArrayList<String> sequence){
        this.sequence = sequence;
    }

}
