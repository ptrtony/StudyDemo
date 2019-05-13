package com.zhaofan.studaydemo.business;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */

public class Stock extends AbstractColleage{


    public static int COMPUTER_NUMBER=100;

    public Stock(AbstractMediator mediator) {
        super(mediator);
    }


    public void increase(int number){
        COMPUTER_NUMBER+=number;
    }


    public void decrease(int number){
        COMPUTER_NUMBER-=number;
    }

    public int getStockNumber(){
        return COMPUTER_NUMBER;
    }

    public void clearStock(){
        super.mediator.excute("stock.clearStock");
    }
}
