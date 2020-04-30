package com.liuchuanzheng.lcz_wanandroid_kotlin.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import kotlinx.android.synthetic.main.fragment_progress_dialog.*

/**
 * @author 刘传政
 * @date 2020/4/30 10:43
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class ProgressDialogFragment : DialogFragment() {
    private var messageResId: Int? = null

    companion object {
        fun newInstance() =
            ProgressDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvMessage.text = getString(messageResId ?: R.string.loading)
    }

    fun show(
        fragmentManager: FragmentManager,
        @StringRes messageResId: Int,
        isCancelable: Boolean = false
    ) {
        this.messageResId = messageResId
        this.isCancelable = isCancelable
        show(fragmentManager, "progressDialogFragment")
    }
}