package com.zhaofan.studaydemo.component_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class RedTea extends Beverage {
    @Override
    public String getDescription() {
        return "红茶";
    }

    @Override
    public double cost() {
        return 9;
    }
}
