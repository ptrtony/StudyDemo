package com.zhaofan.studaydemo.game;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class GameClient {
    public static void main(String[] args){
        IGamePlayer player = new GamePlayer("张三");
        InvocationHandler handler = new GamePlayIH(player);
        ClassLoader classLoader = player.getClass().getClassLoader();
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(classLoader,new Class[]{IGamePlayer.class},handler);
        proxy.login("zhangsan","password");
    }
}
