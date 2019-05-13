package com.zhaofan.studaydemo.factory;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/11
 * description:
 */
public abstract class AbstractSearch {
    protected IGreatTemperamenetGirl iGreatTemperamenetGirl;
    protected IGoodBodyGirl iGoodBodyGirl;
    public AbstractSearch(IGreatTemperamenetGirl iGreatTemperamenetGirl){
        this.iGreatTemperamenetGirl = iGreatTemperamenetGirl;
    }
    public AbstractSearch(IGoodBodyGirl iGoodBodyGirl){
        this.iGoodBodyGirl = iGoodBodyGirl;
    }

    abstract void show();
}
