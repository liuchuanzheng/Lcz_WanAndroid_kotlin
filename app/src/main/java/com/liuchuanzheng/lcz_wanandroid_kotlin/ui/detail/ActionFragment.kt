package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import com.liuchuanzheng.lcz_wanandroid_kotlin.ext.copyTextIntoClipboard
import com.liuchuanzheng.lcz_wanandroid_kotlin.ext.showToast
import com.liuchuanzheng.lcz_wanandroid_kotlin.model.bean.Article
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.detail.DetailActivity
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.detail.DetailActivity.Companion.PARAM_ARTICLE
import kotlinx.android.synthetic.main.fragment_detail_acitons.*

/**
 * Created by xiaojianjun on 2019-11-21.
 */
class ActionFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(article: Article): ActionFragment {
            return ActionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARAM_ARTICLE, article)
                }
            }
        }
    }

    private var behavior: BottomSheetBehavior<View>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_acitons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            val article = getParcelable<Article>(PARAM_ARTICLE) ?: return@run
            llCollect.visibility = if (article.id != 0) View.VISIBLE else View.GONE
            ivCollect.isSelected = article.collect
            tvCollect.text =
                getString(if (article.collect) R.string.cancel_collect else R.string.add_collect)
            llCollect.setOnClickListener {

            }
            llShare.setOnClickListener {

            }
            llExplorer.setOnClickListener {

                behavior?.state = BottomSheetBehavior.STATE_HIDDEN
            }
            llCopy.setOnClickListener {
                context?.copyTextIntoClipboard(article.link, article.title)
                context?.showToast(R.string.copy_success)
                behavior?.state = BottomSheetBehavior.STATE_HIDDEN
            }
            llRefresh.setOnClickListener {
                (activity as? DetailActivity)?.refreshPage()
                behavior?.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val bottomSheet: View = (dialog as BottomSheetDialog).delegate
            .findViewById(com.google.android.material.R.id.design_bottom_sheet)
            ?: return
        behavior = BottomSheetBehavior.from(bottomSheet)
        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun show(manager: FragmentManager) {
        if (!this.isAdded) {
            super.show(manager, "ActionFragment")
        }
    }
}