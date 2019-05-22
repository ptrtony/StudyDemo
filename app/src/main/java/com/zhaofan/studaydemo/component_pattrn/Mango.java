package com.zhaofan.studaydemo.component_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class Mango extends Condiment {
    public Beverage beverage;
    public Mango(Beverage beverage){
        this.beverage = beverage;
    }
    @Override
    public String getDescription() {
        return beverage.getDescription()+", 加芒果";
    }

    @Override
    public double cost() {
        return beverage.cost()+3;
    }
}
