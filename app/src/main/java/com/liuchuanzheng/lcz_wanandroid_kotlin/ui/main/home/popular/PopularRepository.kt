package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.popular

import com.liuchuanzheng.lcz_wanandroid_kotlin.model.api.RetrofitClient

/**
 * Created by xiaojianjun on 2019-09-18.
 */
class PopularRepository {
    suspend fun getTopArticleList() = RetrofitClient.apiService.getTopArticleList().apiData()
    suspend fun getArticleList(page: Int) = RetrofitClient.apiService.getArticleList(page).apiData()
}