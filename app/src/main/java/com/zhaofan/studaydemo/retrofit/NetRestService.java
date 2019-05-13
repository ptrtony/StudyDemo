package com.zhaofan.studaydemo.retrofit;

import okhttp3.Call;

/**
 * @author ChengJingQiang
 * @copyright:2019
 * @project NettyChat
 * @date 2019/5/5
 * description:
 */



public interface NetRestService {

    @GET("http://gank.io/api/today")
    public Call todayGank();
}
