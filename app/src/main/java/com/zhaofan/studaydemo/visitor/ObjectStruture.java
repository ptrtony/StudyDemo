package com.zhaofan.studaydemo.visitor;

import java.util.Random;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class ObjectStruture {
    public static Element createElement(){
        Random random = new Random();
        if (random.nextInt(100)>50){
            return new ConcreteElement1();
        }else{
            return new ConcreteElement2();
        }
    }
}
