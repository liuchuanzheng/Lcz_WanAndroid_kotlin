package com.xiaojianjun.wanandroid.model.api

import com.liuchuanzheng.lcz_wanandroid_kotlin.model.bean.Article
import com.liuchuanzheng.lcz_wanandroid_kotlin.model.bean.Pagination
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by xiaojianjun on 2019-09-18.
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/article/listproject/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int): ApiResult<Pagination<Article>>

    @GET("/article/top/json")
    suspend fun getTopArticleList(): ApiResult<List<Article>>

    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>

    @GET("/user_article/list/{page}/json")
    suspend fun getUserArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>


}