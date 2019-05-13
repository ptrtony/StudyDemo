package com.zhaofan.studaydemo.enviroment;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class Calcilator {
    private Expression expression;
    public Calcilator(String expStr){
        Stack<Expression> stack = new Stack<>();
        char[] charArray = expStr.toCharArray();
        Expression left = null;
        Expression right = null;
        for (int i=0;i<charArray.length;i++){
            switch (charArray[i]){
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddExpression(left,right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left,right));
                    break;
                    default:
                        stack.push(new VarExpression(String.valueOf(charArray[++i])));

            }
        }
        this.expression = stack.pop();
    }


    public int run(HashMap<String,Integer> var){
        return this.expression.interpreter(var);
    }
}
