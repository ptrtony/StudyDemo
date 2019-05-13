package com.zhaofan.studaydemo.business;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public class Client {
    public static void main(String[] args){
        Context context = new Context(new ItorateOne());
        context.exec();

        Context context1 = new Context(new ItorateTwo());
        context1.exec();

        Context context2 = new Context(new ItorateThree());
        context2.exec();
    }
}
