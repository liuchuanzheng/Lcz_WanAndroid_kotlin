package com.liuchuanzheng.lcz_wanandroid_kotlin

import android.app.Application
import com.liuchuanzheng.lcz_wanandroid_kotlin.common.ActivityLifecycleCallBacksAdapter
import com.liuchuanzheng.lcz_wanandroid_kotlin.util.core.ActivityManager
import com.liuchuanzheng.lcz_wanandroid_kotlin.util.isMainProcess

/**
 * @author 刘传政
 * @date 2020/4/29 14:12
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class MyApplication : Application() {
    //静态类
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //主进程
        if (isMainProcess(this)) {
            init()
        }

    }

    private fun init() {
        //记录打开的activity.方便随时管理
        registerActivityLifecycleCallbacks(ActivityLifecycleCallBacksAdapter(
            onActivityCreatedBlock = { activity, savedInstanceState ->
                ActivityManager.activitys.add(activity)
            },
            onActivityDestroyedBlock = { activity ->
                ActivityManager.activitys.remove(activity)
            }
        ))
    }
}