package com.zhaofan.studaydemo.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 *@copyright:2019
 *@project NettyChat
 *@author ChengJingQiang
 *@date 2019/5/10
 *description:
 */

val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .build()

val service = retrofit.create(GithubService::class.java)

fun main(){
    val repos = service.listRepos("octocat")
    repos.enqueue(object: Callback<List<Repo>> {
        override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

        }

        override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {

        }

    })
}