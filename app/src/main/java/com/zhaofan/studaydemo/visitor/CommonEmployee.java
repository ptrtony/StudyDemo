package com.zhaofan.studaydemo.visitor;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class CommonEmployee extends Employee {
    @Override
    protected String getOtherInfo() {
        return "";
    }

    @Override
    protected void accept(IVisitor visitor) {
        visitor.visit(this);
    }



}
