package com.liuchuanzheng.lcz_wanandroid_kotlin.util.core

import android.app.Activity
import android.content.Intent
import com.liuchuanzheng.lcz_wanandroid_kotlin.ext.putExtras

/**
 * @author 刘传政
 * @date 2020/4/29 14:50
 * QQ:1052374416
 * 电话:18501231486
 * 作用:单例类.最适合当util的写法
 * 注意事项:
 */
object ActivityManager {
    val activities = mutableListOf<Activity>()
    fun start(clazz: Class<out Activity>, params: Map<String, Any> = emptyMap()): Unit {
        val currentActivity = activities[activities.lastIndex]
        val intent = Intent(currentActivity, clazz)
        params.forEach {
            intent.putExtras(it.key to it.value)
        }
        currentActivity.startActivity(intent)
    }

    /**
     * finish指定的一个或多个Activity
     */
    fun finish(vararg clazz: Class<out Activity>) {
        activities.forEach { activiy ->
            if (clazz.contains(activiy::class.java)) {
                activiy.finish()
            }
        }
    }
}