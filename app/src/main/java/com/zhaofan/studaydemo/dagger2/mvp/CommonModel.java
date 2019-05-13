package com.zhaofan.studaydemo.dagger2.mvp;

import dagger.Module;
import dagger.Provides;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/8
 * description:
 */

@Module
public class CommonModel {
    private ICommonView iCommonView;
    public CommonModel(ICommonView iCommonView){
        this.iCommonView = iCommonView;
    }

    @Provides
    @ActivityScope
    public ICommonView providesICommonView(){
        return this.iCommonView;
    }
}
