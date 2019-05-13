package com.zhaofan.studaydemo.game;

import android.util.Log;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class GamePlayer implements IGamePlayer {
    private static final String TAG = "GamePlayer";
    private String name="";
    public GamePlayer(String _name){
        this.name = _name;
    }


    @Override
    public void login(String username, String password) {
       Log.d(TAG,"登录名为"+username+"用户名"+this.name+"成功登录！");
    }

    @Override
    public void killBoss() {
        Log.d(TAG,this.name+"正在打怪！");
    }

    @Override
    public void upgrade() {
        Log.d(TAG,this.name+"又升了一级");
    }
}
