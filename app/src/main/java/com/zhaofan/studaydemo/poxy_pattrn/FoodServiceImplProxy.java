package com.zhaofan.studaydemo.poxy_pattrn;

import com.zhaofan.studaydemo.factory_pattrn.Food;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class FoodServiceImplProxy implements FoodService {

    private FoodServiceImpl foodService;
    FoodServiceImplProxy(FoodServiceImpl foodService){
        this.foodService = foodService;
    }
    @Override
    public Food makeChicken() {
        System.out.println("我们马上要开始制作鸡肉了");
        Food food = foodService.makeChicken();
        System.out.println("鸡肉制作完成啦，加点胡椒粉"); // 增强
        food.addCondiment("pepper");
        return null;
    }

    @Override
    public Food makeNoodel() {
        System.out.println("准备制作拉面~");
        Food food = foodService.makeNoodel();
        System.out.println("制作完成啦");
        return food;
    }
}
