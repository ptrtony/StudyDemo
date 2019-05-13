package com.zhaofan.studaydemo.game;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public interface IGamePlayer {
    public void login(String username,String password);

    public void killBoss();

    public void upgrade();
}
