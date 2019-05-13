package com.zhaofan.studaydemo.factory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/11
 * description:
 */
public class Client {
    public static void main(String[] args){

        Teacher teacher = new Teacher();
        List<Gril> grils = new ArrayList<>();
        for (int i=0;i<20;i++){
            grils.add(new Gril());
        }
        teacher.common(new GroupLoader(grils));


        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);

    }
}
