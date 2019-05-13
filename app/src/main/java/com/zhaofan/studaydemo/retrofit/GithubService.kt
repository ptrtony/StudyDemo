package com.zhaofan.studaydemo.retrofit

import com.zhaofan.studaydemo.rxjava.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *@copyright:2019
 *@project NettyChat
 *@author ChengJingQiang
 *@date 2019/5/10
 *description:
 */
public interface GithubService{
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user:String): Call<List<Repo>>

    @GET("users/rengwuxian")
    fun getUser():Call<User>
}





