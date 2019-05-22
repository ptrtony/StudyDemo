package com.zhaofan.studaydemo.factory_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class LanZhouFood implements FoodFactory{
    @Override
    public Food makeFood(String make) {
        if (make.equals("A")){
            return new XiaoChaoRouFood();
        }
        return null;
    }
}
