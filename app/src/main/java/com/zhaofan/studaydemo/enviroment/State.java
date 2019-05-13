package com.zhaofan.studaydemo.enviroment;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public abstract class State {
    protected Context context;

    public void setContext(Context _context){
        this.context = _context;
    }

    //行为一
    public abstract void handle1();
    //行为二
    public abstract void handle2();
}


