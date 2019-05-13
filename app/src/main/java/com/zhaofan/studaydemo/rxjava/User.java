package com.zhaofan.studaydemo.rxjava;



import java.util.List;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/4/30
 * description:
 */
public class User {
    public String userName;
    public List<Address> addresses;

    public static class Address{
        public String street;
        public String city;
    }
}
