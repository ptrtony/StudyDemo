package com.zhaofan.studaydemo.poxy_pattrn;

import com.zhaofan.studaydemo.factory_pattrn.Food;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class Chicken implements Food {
    private int chicken;
    private int spicy;
    private int salt;
    private String comdiment;
    public int getChicken() {
        return chicken;
    }

    public void setChicken(int chicken) {
        this.chicken = chicken;
    }

    public int getSpicy() {
        return spicy;
    }

    public void setSpicy(int spicy) {
        this.spicy = spicy;
    }

    public int getSalt() {
        return salt;
    }


    @Override
    public void setSale(int sale) {
        this.salt = sale;
    }

    @Override
    public void addCondiment(String comdiment) {
        this.comdiment = comdiment;
    }

    public String getComdiment(){
        return comdiment;
    }
}
