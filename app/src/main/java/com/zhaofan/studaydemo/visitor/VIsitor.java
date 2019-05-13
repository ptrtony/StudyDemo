package com.zhaofan.studaydemo.visitor;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class VIsitor implements IVisitor {
    @Override
    public void visit(ConcreteElement1 concreteElement1) {
        concreteElement1.doSomething();
    }

    @Override
    public void visit(ConcreteElement2 concreteElement2) {
        concreteElement2.doSomething();
    }

    @Override
    public void visit(CommonEmployee commonEmployee) {

    }

    @Override
    public void visit(Mamager mamager) {

    }
}
