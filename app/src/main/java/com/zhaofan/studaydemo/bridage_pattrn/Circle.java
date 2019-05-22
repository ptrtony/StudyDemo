package com.zhaofan.studaydemo.bridage_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class Circle extends Shape {
    private int radius;
    public Circle(int radius,DrawAPI drawAPI){
        super(drawAPI);
        this.radius = radius;
    }


    @Override
    public void draw() {
        drawAPI.draw(radius,0,0);
    }
}
