package com.zhaofan.studaydemo.visitor;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class Client {
    public static void main(String[] args){
        for (int i=0;i<100;i++){
            Element element = ObjectStruture.createElement();
            element.accept(new VIsitor());
        }
    }
}
