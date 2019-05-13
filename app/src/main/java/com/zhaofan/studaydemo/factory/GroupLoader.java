package com.zhaofan.studaydemo.factory;

import android.util.Log;

import java.util.List;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/11
 * description:
 */
public class GroupLoader {
    private List<Gril> grils;
    public GroupLoader(List<Gril> grils){
        this.grils = grils;
    }

    public void countGirl(){
        Log.d("GroupLoader","girl number is "+grils.size());
    }
}
