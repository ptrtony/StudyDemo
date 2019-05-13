package com.zhaofan.studaydemo.dagger2.mvp;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project Wepay
 * @date 2019/4/8
 * description:
 */
public class UserResp<T> {
    public String errormsg;
    public int code;
    public T result;
}
