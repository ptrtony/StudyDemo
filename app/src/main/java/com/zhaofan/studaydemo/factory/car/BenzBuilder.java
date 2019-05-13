package com.zhaofan.studaydemo.factory.car;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class BenzBuilder extends CarBuilder {
    private BenzCarModel benzCarModel = new BenzCarModel();
    @Override
    public void setSequence(ArrayList<String> sequence) {
        benzCarModel.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benzCarModel;
    }
}
