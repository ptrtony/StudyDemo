package com.zhaofan.studaydemo.game;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class Thing implements Cloneable{
    //定义一个私用变量
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected Thing clone() throws CloneNotSupportedException {
        Thing thing = null;
        thing = (Thing) super.clone();
        return thing;
    }


    //设置hashmap的值
    public void setValue(String value){
        this.arrayList.add(value);
    }

    public ArrayList<String> getValue(){
        return this.arrayList;
    }
}
