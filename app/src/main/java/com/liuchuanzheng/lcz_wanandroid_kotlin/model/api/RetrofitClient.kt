package com.liuchuanzheng.lcz_wanandroid_kotlin.model.api

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.liuchuanzheng.lcz_wanandroid_kotlin.MyApplication
import com.xiaojianjun.wanandroid.model.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by xiaojianjun on 2019-09-18.
 */
object RetrofitClient {
    private val cookieJar = PersistentCookieJar(
        SetCookieCache(),
        SharedPrefsCookiePersistor(MyApplication.instance)
    )

    private fun getOkHttpClient(): OkHttpClient {

        // 创建日志拦截器 HttpLogger() 是自定义的类
        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            // 添加日志拦截拦截器
            .addNetworkInterceptor(logInterceptor)
            .build()
    }


    private val retrofit = Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)

    fun clearCookie() = cookieJar.clear()
}