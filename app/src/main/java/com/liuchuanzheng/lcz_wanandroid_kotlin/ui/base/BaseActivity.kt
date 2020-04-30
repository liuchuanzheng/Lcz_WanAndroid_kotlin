package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.liuchuanzheng.lcz_wanandroid_kotlin.common.ProgressDialogFragment

/**
 * @author 刘传政
 * @date 2020/4/30 10:32
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
abstract class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialogFragment: ProgressDialogFragment
    /**
     * 加载状态有4种：
     * 1.整页数据加载，加载动画在页面中间
     * 2.下拉刷新
     * 3.分页加载更多
     * 4.数据提交服务器加载对话框
     */

    /**
     * 加载结果：
     * 1.空，无数据
     * 2.无网络
     * 3.失败，点击重试
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
    }

    open fun layoutRes() = 0
    fun showProgressDialog(@StringRes message: Int) {
        if (this::progressDialogFragment.isInitialized) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        progressDialogFragment.show(supportFragmentManager, message, false)
    }

    fun hideProgressDialog() {
        if (this::progressDialogFragment.isInitialized && progressDialogFragment.isVisible) {
            progressDialogFragment.dismiss()
        }
    }
}