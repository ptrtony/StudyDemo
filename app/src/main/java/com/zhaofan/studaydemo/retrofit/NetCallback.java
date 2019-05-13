package com.zhaofan.studaydemo.retrofit;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/5
 * description:
 */
public interface NetCallback<T> {
    void onFailure(Exception e);
    void onSuccess(T data);
}
