package com.zhaofan.studaydemo.poxy_pattrn;

import com.zhaofan.studaydemo.factory_pattrn.Food;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class Noodel implements Food {
    private int noodel;
    private String comdiment;
    public int getNoodel() {
        return noodel;
    }

    public void setNoodel(int noodel) {
        this.noodel = noodel;
    }

    @Override
    public void setSale(int sale) {

    }

    @Override
    public void addCondiment(String comdiment) {
        this.comdiment = comdiment;
    }

    public String addComdiment(){
        return comdiment;
    }
}
