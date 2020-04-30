package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.splash

import android.os.Bundle
import com.liuchuanzheng.lcz_wanandroid_kotlin.MainActivity
import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseActivity
import com.liuchuanzheng.lcz_wanandroid_kotlin.util.core.ActivityManager

/**
 * @author 刘传政
 * @date 2020/4/30 11:12
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class SplashActivity : BaseActivity() {
    override fun layoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.postDelayed({
            ActivityManager.start(MainActivity::class.java)
            ActivityManager.finish(SplashActivity::class.java)
        }, 1000)
    }
}