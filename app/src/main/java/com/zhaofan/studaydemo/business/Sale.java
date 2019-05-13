package com.zhaofan.studaydemo.business;

import android.util.Log;

import java.util.Random;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class Sale extends AbstractColleage{


    private static final String TAG="sale";

    public Sale(AbstractMediator mediator) {
        super(mediator);
    }

    public void sellIBMComputer(int number){

    }

    public int getSaleStatus(){
        Random random = new Random(System.currentTimeMillis());
        int scaleStatus = random.nextInt(100);
        return scaleStatus;
    }


    public void offSale(){
        super.mediator.excute("sale.offSale");
    }
}


