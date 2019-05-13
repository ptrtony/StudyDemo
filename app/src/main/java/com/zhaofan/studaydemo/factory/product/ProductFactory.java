package com.zhaofan.studaydemo.factory.product;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/12
 * description:
 */
public class ProductFactory {
    private static final Map<String,Product> prMap = new HashMap<>();
    public static synchronized Product createProduct(String type){
        Product product = null;
        if (prMap.containsKey(type)){
            product = prMap.get(type);
        }else{
            if (type.equals("Product1")){
                product = new ConcreteProduct1();
            }else{
                product = new ConcreteProduct2();
            }
        }
        return product;
    }
}
