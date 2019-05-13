package com.zhaofan.studaydemo.iterator;

import java.util.ArrayList;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public class Project implements IProject {
    private ArrayList<IProject> projectList = new ArrayList<>();
    private String name="";
    private int num = 0;
    private int cost=0;
    public Project(){

    }

    private Project(String _name,int _num,int _cost){
        this.name = _name;
        this.num = _num;
        this.cost=_cost;
    }



    @Override
    public String getProjectInfo() {
        String info="";
        info = info+"项目名称是："+this.name;
        info = info+"\t项目人数:"+this.num;
        info = info+"\t项目费用:"+this.cost;
        return info;
    }
    //增加项目
    @Override
    public void add(String _name,int _num,int _cost) {
        this.projectList.add(new Project(_name,_num,_cost));
    }

    @Override
    public IProjectIterator iterator() {
        return new ProjectIterator(this.projectList) ;
    }
}
