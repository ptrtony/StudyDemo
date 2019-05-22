package com.zhaofan.studaydemo.poxy_pattrn;

import com.zhaofan.studaydemo.factory_pattrn.Food;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class FoodServiceImpl implements FoodService {
    @Override
    public Food makeChicken() {
        Food chicken = new Chicken();
        ((Chicken) chicken).setChicken(1000);
        chicken.setSale(500);
        ((Chicken) chicken).setSpicy(300);
        return null;
    }

    @Override
    public Food makeNoodel() {
        Food noodel = new Noodel();
        noodel.setSale(500);
        ((Noodel) noodel).setNoodel(1000);
        return noodel;
    }
}
