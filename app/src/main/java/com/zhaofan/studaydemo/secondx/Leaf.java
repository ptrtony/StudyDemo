package com.zhaofan.studaydemo.secondx;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public class Leaf implements ILeaf {
    private String name = "";
    private String position = "";
    private int salary = 0;
    public Leaf(String _name,String _position,int _salary){
        this.name = _name;
        this.position = _position;
        this.salary =_salary;
    }
    @Override
    public String getInfo() {
        String info = "";
        info = "名称："+this.name;
        info = info+"\t职位："+this.position;
        info = info +"\t薪水："+this.salary;
        return info;
    }
}
