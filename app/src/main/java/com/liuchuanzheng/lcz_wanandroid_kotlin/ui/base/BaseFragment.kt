package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * @author 刘传政
 * @date 2020/5/6 10:18
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
open class BaseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes(), container, false)
    }

    open fun layoutRes(): Int {
        return 0
    }
}