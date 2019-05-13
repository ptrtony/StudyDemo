package com.zhaofan.studaydemo.factory;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/15
 * description:
 */
public enum  Calculator {
    ADD("+"){
        public int exec(int a,int b){
            return a+b;
        }
    },
    SUB("-"){
        public int exec(int a,int b){
            return a-b;
        }
    };
    String value = "";

    Calculator(String s) {
        this.value = s;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }


    public abstract int exec(int a,int b);
}
