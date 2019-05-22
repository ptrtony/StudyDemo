package com.zhaofan.studaydemo.component_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:装饰
 */
public abstract class Beverage {
    //返回描述
    public abstract String getDescription();
    //价格
    public abstract double cost();
}
