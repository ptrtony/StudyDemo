package com.zhaofan.studaydemo.game;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class Proxy implements Subject {
    private Subject subject = null;
    public Proxy(Subject _subject){
        this.subject = _subject;
    }

    @Override
    public void request() {

    }


}
