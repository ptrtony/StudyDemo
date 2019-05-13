package com.zhaofan.studaydemo.enviroment;

import java.util.HashMap;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public abstract class Expression {
    public abstract int interpreter(HashMap<String,Integer> var);
}
