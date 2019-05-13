package com.zhaofan.studaydemo.enviroment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/17
 * description:
 */
public class ExpermissionClient {
    public static void main(String[] args) throws IOException {
        String expStr = getExpStr();
        HashMap<String,Integer> var = getValue(expStr);
        Calcilator cl = new Calcilator(expStr);
        System.out.println("运算结果为："+expStr+"="+cl.run(var));
    }

    //获得表达式

    public static String getExpStr() throws IOException {
        System.out.println("请输入表达式：");
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    //获得值映射
    public static HashMap<String,Integer> getValue(String expStr) throws IOException {
        HashMap<String,Integer> map = new HashMap<>();
        for (char ch : expStr.toCharArray()){
            if (ch!='+' && ch !='-'){
                if (map.containsKey(String.valueOf(ch))){
                    String in = new BufferedReader(new InputStreamReader(System.in)).readLine();
                    map.put(String.valueOf(ch),Integer.parseInt(in));
                }
            }
        }
        return map;
    }
}
