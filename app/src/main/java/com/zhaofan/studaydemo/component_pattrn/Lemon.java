package com.zhaofan.studaydemo.component_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class Lemon extends Condiment{
    private Beverage bevarage;
    public Lemon(Beverage bevarage){
        this.bevarage = bevarage;
    }
    @Override
    public String getDescription() {
        return bevarage.getDescription() + ", 加柠檬";
    }

    @Override
    public double cost() {
        return bevarage.cost()+2;
    }
}
