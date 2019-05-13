package com.zhaofan.studaydemo.observal;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public class HanFeiZi implements IHanFeiZi {
    private boolean isHavingBrankfast = false;

    private boolean isHavingFun = false;

    @Override
    public void haveBreakfast() {
        System.out.println("韩非子：开始吃饭了...");
        this.isHavingBrankfast = true;
    }

    @Override
    public void haveFun() {
        System.out.println("韩非子：开始娱乐了...");
        this.isHavingFun = true;
    }



    public boolean isHavingBrankfast(){
        return isHavingBrankfast;
    }

    public void setHavingBrankfast(boolean isHavingBrankfast){
        this.isHavingBrankfast = isHavingBrankfast;
    }
    public boolean isHavingFun(){
        return isHavingFun;
    }


    public void setHavingFun(boolean isHavingFun){
        this.isHavingFun = isHavingFun;
    }



}

