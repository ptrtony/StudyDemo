package com.zhaofan.studaydemo.poxy_pattrn;

import com.zhaofan.studaydemo.factory_pattrn.Food;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public interface FoodService {
    Food makeChicken();
    Food makeNoodel();
}
