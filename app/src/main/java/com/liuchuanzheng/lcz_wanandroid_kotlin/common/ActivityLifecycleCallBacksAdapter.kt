package com.liuchuanzheng.lcz_wanandroid_kotlin.common

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @author 刘传政
 * @date 2020/4/29 14:19
 * QQ:1052374416
 * 电话:18501231486
 * 作用:使用者需要用哪个生命周期再用,不用一次性全写出来
 * 注意事项:
 */
class ActivityLifecycleCallBacksAdapter(
    //传进来一个表达式
    private var onActivityCreatedBlock: ((activity: Activity, savedInstanceState: Bundle?) -> Unit)? = null,
    private var onActivityStartedBlock: ((activity: Activity) -> Unit)? = null,
    private var onActivityResumedBlock: ((activity: Activity) -> Unit)? = null,
    private var onActivitySaveInstanceStateBlock: ((activity: Activity, outState: Bundle?) -> Unit)? = null,
    private var onActivityPausedBlock: ((activity: Activity) -> Unit)? = null,
    private var onActivityStoppedBlock: ((activity: Activity) -> Unit)? = null,
    private var onActivityDestroyedBlock: ((activity: Activity) -> Unit)? = null
) : Application.ActivityLifecycleCallbacks {
    /**
     * Called when the Activity calls [super.onPause()][Activity.onPause].
     */
    override fun onActivityPaused(activity: Activity) {
        onActivityPausedBlock?.invoke(activity)
    }

    /**
     * Called when the Activity calls [super.onStart()][Activity.onStart].
     */
    override fun onActivityStarted(activity: Activity) {
        onActivityStartedBlock?.invoke(activity)
    }

    /**
     * Called when the Activity calls [super.onDestroy()][Activity.onDestroy].
     */
    override fun onActivityDestroyed(activity: Activity) {
        onActivityDestroyedBlock?.invoke(activity)
    }

    /**
     * Called when the Activity calls
     * [super.onSaveInstanceState()][Activity.onSaveInstanceState].
     */
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        onActivitySaveInstanceStateBlock?.invoke(activity, outState)
    }

    /**
     * Called when the Activity calls [super.onStop()][Activity.onStop].
     */
    override fun onActivityStopped(activity: Activity) {
        onActivityStoppedBlock?.invoke(activity)
    }

    /**
     * Called when the Activity calls [super.onCreate()][Activity.onCreate].
     */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        onActivityCreatedBlock?.invoke(activity, savedInstanceState)
    }

    /**
     * Called when the Activity calls [super.onResume()][Activity.onResume].
     */
    override fun onActivityResumed(activity: Activity) {
        onActivityResumedBlock?.invoke(activity)
    }

}