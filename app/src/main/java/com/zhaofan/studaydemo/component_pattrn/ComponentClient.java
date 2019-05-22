package com.zhaofan.studaydemo.component_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class ComponentClient {
    public static void main(String[] arg){
        Beverage beverage =new Lemon(new Lemon(new RedTea()));
    }
}
