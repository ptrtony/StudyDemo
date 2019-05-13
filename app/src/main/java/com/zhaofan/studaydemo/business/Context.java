package com.zhaofan.studaydemo.business;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public class Context {
    private Itorrate itorrate;
    public Context(Itorrate _itorrate){
        this.itorrate = _itorrate;
    }

    public void exec(){
        itorrate.operate();
    }
}
