package com.zhaofan.studaydemo.builder_pattrn;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/21
 * description:
 */
public class User {
    //属性
    private String name;
    private String password;
    private int age;
    private String nickName;

    public User(String name, String password, int age, String nickName) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.nickName = nickName;
    }


    public static class UserBuilder{
        private String name;
        private String password;
        private int age;
        private String nickName;
        public UserBuilder(){
        }
        //链式调用设备的各个属性值，即UserBuilder
        public UserBuilder name(String name){
            this.name = name;
            return this;
        }

        public UserBuilder password(String password){
            this.password = password;
            return this;
        }

        public UserBuilder age(int age){
            this.age = age;
            return this;
        }

        public UserBuilder nickName(String nickName){
            this.nickName = nickName;
            return this;
        }


        public User build(){
            if (name==null){
                throw new RuntimeException("用户名和密码必填");
            }
            if (age <= 0 || age >= 150) {
                throw new RuntimeException("年龄不合法");
            }
            // 还可以做赋予”默认值“的功能
            if (nickName == null) {
                nickName = name;
            }
            return new User(name,password,age,nickName);
        }
    }
}
