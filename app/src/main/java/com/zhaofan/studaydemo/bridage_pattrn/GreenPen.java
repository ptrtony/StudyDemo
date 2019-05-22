package com.zhaofan.studaydemo.bridage_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class GreenPen implements DrawAPI {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("用绿色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}
