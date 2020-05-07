package com.xiaojianjun.wanandroid.ui.main.home.latest

import com.liuchuanzheng.lcz_wanandroid_kotlin.model.api.RetrofitClient

/**
 * Created by xiaojianjun on 2019-09-18.
 */
class LatestRepository {
    suspend fun getProjectList(page: Int) = RetrofitClient.apiService.getProjectList(page).apiData()
}