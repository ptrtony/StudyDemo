package com.zhaofan.studaydemo.iterator;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public interface IProject {
    String getProjectInfo();
    void add(String _name,int _num,int _cost);
    IProjectIterator iterator();
}
