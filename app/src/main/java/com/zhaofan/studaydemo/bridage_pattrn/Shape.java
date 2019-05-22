package com.zhaofan.studaydemo.bridage_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public abstract class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
