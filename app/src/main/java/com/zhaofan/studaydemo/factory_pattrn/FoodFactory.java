package com.zhaofan.studaydemo.factory_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description: 食物工厂接口
 */
public interface FoodFactory {
    Food makeFood(String make);
}
