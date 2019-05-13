package com.zhaofan.studaydemo.secondx;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public interface IBranch {
    public String getInfo();
    public void add(IBranch branch);
    public void add(ILeaf leaf);
    public ArrayList getSubordinateInfo();
}
