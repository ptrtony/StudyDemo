package com.zhaofan.studaydemo.business;

import android.util.Log;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class Meidiator extends AbstractMediator {
    private static final String TAG = "Meidiator";

    //中介者最重要的方法
    @Override
    public void excute(String str, Object... objects) {
        if (str.equals("purchase.buy")){
            this.buyComputer((Integer) objects[0]);
        }else if (str.equals("sale.sell")){
            this.saleComputer((Integer) objects[0]);
        }else if (str.equals("sale.offsell")){
            this.offSell();
        }else if (str.equals("stock.clear")){
            this.clearStock();
        }
    }
    //采购电脑
    private void buyComputer(int number){
        int saleStatus = super.sale.getSaleStatus();
        if (saleStatus>80){
            Log.d(TAG,"采购IBM电脑："+number+"台");
            stock.increase(number);
        }else{
            int buyNumber = number/2;
            Log.d(TAG,"采购IBM电脑："+buyNumber+"台");
        }
    }

    //销售电脑
    private void saleComputer(int number){
        if (super.stock.getStockNumber()<number){
           super.purchase.buyIBMComputer(number);
        }

        super.stock.decrease(number);
    }


    //折价销售电脑
    private void offSell(){
        Log.d(TAG,"折价销售IBM电脑"+stock.getStockNumber());
    }

    private void clearStock(){
        super.sale.offSale();
        super.purchase.refuseBuyIBM();
    }
}
