package com.zhaofan.studaydemo.game;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class GamePlayerProxy implements IGamePlayer {
    private IGamePlayer iGamePlayer;
    public GamePlayerProxy(IGamePlayer _iGamePlayer){
        this.iGamePlayer = _iGamePlayer;
    }

    @Override
    public void login(String username, String password) {
        this.iGamePlayer.login(username, password);
    }

    @Override
    public void killBoss() {
        this.iGamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.iGamePlayer.upgrade();
    }
}
