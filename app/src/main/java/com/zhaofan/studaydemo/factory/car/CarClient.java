package com.zhaofan.studaydemo.factory.car;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class CarClient {
    public static final void main(String[] args){
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        BenzBuilder benzBuilder = new BenzBuilder();
        benzBuilder.setSequence(sequence);
        BenzCarModel benzCarModel = (BenzCarModel) benzBuilder.getCarModel();
        benzCarModel.run();
    }
}
