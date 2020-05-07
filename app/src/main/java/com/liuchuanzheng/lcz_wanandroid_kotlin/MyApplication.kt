package com.liuchuanzheng.lcz_wanandroid_kotlin

import android.app.Application
import com.liuchuanzheng.lcz_wanandroid_kotlin.common.ActivityLifecycleCallBacksAdapter
import com.liuchuanzheng.lcz_wanandroid_kotlin.util.core.ActivityManager
import com.liuchuanzheng.lcz_wanandroid_kotlin.util.isMainProcess
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


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
                ActivityManager.activities.add(activity)
            },
            onActivityDestroyedBlock = { activity ->
                ActivityManager.activities.remove(activity)
            }
        ))

        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(2) // (Optional) How many method line to show. Default 2
            .methodOffset(0) // (Optional) Hides internal method calls up to offset. Default 5
            //                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
            .tag("玩Android") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}