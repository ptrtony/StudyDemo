package com.zhaofan.studaydemo.enviroment;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public abstract class SymboleExpression extends Expression {
    protected Expression left;
    protected Expression right;

    public SymboleExpression(Expression _left,Expression _right){
        this.left = _left;
        this.right = _right;
    }


}
