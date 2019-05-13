package com.zhaofan.studaydemo.retrofit

import com.zhaofan.studaydemo.rxjava.User
import okhttp3.internal.platform.Platform
import retrofit2.Call
import java.lang.reflect.InvocationHandler

/**
 *@copyright:2019
 *@project NettyChat
 *@author ChengJingQiang
 *@date 2019/5/10
 *description:
 */
class RealService :GithubService{
    internal var invocationHandler: InvocationHandler = InvocationHandler { proxy, method, args ->
        val platform :Platform = Platform.get()

        null
    }
    override fun listRepos(user: String): Call<List<Repo>> {
        val method = GithubService::class.java.getMethod("listRepos")
        return invocationHandler.invoke(this,method,null) as Call<List<Repo>>
    }

    override fun getUser(): Call<User> {
        val method = GithubService::class.java.getMethod("getUser")
        return invocationHandler.invoke(this,method,null) as Call<User>
    }
}