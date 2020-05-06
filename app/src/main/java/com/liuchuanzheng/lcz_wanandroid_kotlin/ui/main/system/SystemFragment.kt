package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.system

import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseFragment

/**
 * @author 刘传政
 * @date 2020/5/6 10:23
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class SystemFragment : BaseFragment() {
    override fun layoutRes(): Int {
        return R.layout.fragment_system
    }

    companion object {
        fun newInstance() = SystemFragment()
    }
}