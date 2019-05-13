package com.zhaofan.studaydemo.business;

import android.util.Log;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
//采购
public class Purchase extends AbstractColleage{
    private static final String TAG = "purchase";

    public Purchase(AbstractMediator mediator) {
        super(mediator);
    }

    public void buyIBMComputer(int number){
        super.mediator.excute("purchase.buy",number);
    }


    public void refuseBuyIBM(){
        Log.d(TAG,"不再采购IBM电脑");
    }


}
