package com.zhaofan.studaydemo.secondx;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public class Branch implements IBranch{
    private ArrayList subordinateInfo = new ArrayList();
    private String name = "";
    private String position = "";
    private int salary = 0;
@Override
    public String getInfo() {
        String info = "";
        info = "名称:" + this.name;
        info = info + "\t职位："+this.position;
        info = info +"\t薪水："+this.salary;
        return info;
    }

    @Override
    public void add(IBranch branch) {
        this.subordinateInfo.add(branch);
    }

    @Override
    public void add(ILeaf leaf) {
        this.subordinateInfo.add(leaf);
    }

    @Override
    public ArrayList getSubordinateInfo() {
        return this.subordinateInfo;
    }
}
