package com.zhaofan.studaydemo.observal;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public interface Observable {
    public void addObserver(Observable observable);

    public void deleteObserver(Observable observable);

    public void notifyObservers(String context);
}
