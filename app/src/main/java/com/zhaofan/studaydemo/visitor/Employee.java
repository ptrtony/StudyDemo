package com.zhaofan.studaydemo.visitor;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public abstract class Employee  {
    public static final int MALE = 1;
    public static final int FEMALE = 2;
    private  int salary;

    private  String name;

    private int sex;

    public void setSalary(int salary){
        this.salary = salary;
    }


    public int getSalary(){
        return this.salary;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


    public void setSex(int sex){
        this.sex = sex;
    }

    public int getSex(){
        return this.sex;
    }


    public final void report(){
        String info = "姓名："+this.name+"\t";
        info = info +"性别："+(this.sex == FEMALE?"女性":"男性")+"\t";
        info = info +"薪水："+this.salary+"\t";
        info = info+this.getOtherInfo();
        System.out.println(info);

    }
    protected abstract String getOtherInfo();

    protected abstract void accept(IVisitor visitor);
}
