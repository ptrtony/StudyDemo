package com.zhaofan.studaydemo.visitor;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public interface IVisitor {
    public void visit(ConcreteElement1 concreteElement1);
    public void visit(ConcreteElement2 concreteElement2);
    public void visit(CommonEmployee commonEmployee);
    public void visit(Mamager mamager);
}
