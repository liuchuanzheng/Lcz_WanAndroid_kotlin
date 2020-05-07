package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.latest


import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import com.liuchuanzheng.lcz_wanandroid_kotlin.common.ScrollToTop
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseVMFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.detail.DetailActivity
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.ArticleAdapter
import com.liuchuanzheng.lcz_wanandroid_kotlin.util.core.ActivityManager
import com.xiaojianjun.wanandroid.common.loadmore.CommonLoadMoreView
import com.xiaojianjun.wanandroid.common.loadmore.LoadMoreStatus
import com.xiaojianjun.wanandroid.ui.main.home.latest.LatestViewModel
import kotlinx.android.synthetic.main.fragment_latest.*
import kotlinx.android.synthetic.main.include_reload.*

class LatestFragment : BaseVMFragment<LatestViewModel>(),
    ScrollToTop {

    companion object {
        fun newInstance() = LatestFragment()
    }

    private lateinit var mAdapter: ArticleAdapter

    override fun layoutRes() = R.layout.fragment_latest

    override fun viewModelClass() = LatestViewModel::class.java

    override fun initView() {
        swipeRefreshLayout.run {
            setColorSchemeResources(R.color.textColorPrimary)
            setProgressBackgroundColorSchemeResource(R.color.bgColorPrimary)
            setOnRefreshListener { mViewModel.refreshProjectList() }
        }
        mAdapter = ArticleAdapter(R.layout.item_article).apply {
            setLoadMoreView(CommonLoadMoreView())
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener({
                mViewModel.loadMoreProjectList()
            }, recyclerView)
            setOnItemClickListener { _, _, position ->
                val article = mAdapter.data[position]
                ActivityManager.start(
                    DetailActivity::class.java, mapOf(DetailActivity.PARAM_ARTICLE to article)
                )
            }
            setOnItemChildClickListener { _, view, position ->

            }
        }
        btnReload.setOnClickListener {
            mViewModel.refreshProjectList()
        }
    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            articleList.observe(viewLifecycleOwner, Observer {
                mAdapter.setNewData(it)
            })
            refreshStatus.observe(viewLifecycleOwner, Observer {
                swipeRefreshLayout.isRefreshing = it
            })
            loadMoreStatus.observe(viewLifecycleOwner, Observer {
                when (it) {
                    LoadMoreStatus.COMPLETED -> mAdapter.loadMoreComplete()
                    LoadMoreStatus.ERROR -> mAdapter.loadMoreFail()
                    LoadMoreStatus.END -> mAdapter.loadMoreEnd()
                    else -> return@Observer
                }
            })
            reloadStatus.observe(viewLifecycleOwner, Observer {
                reloadView.isVisible = it
            })
        }

    }

    override fun lazyLoadData() {
        mViewModel.refreshProjectList()
    }

    override fun scrollToTop() {
        recyclerView.smoothScrollToPosition(0)
    }
}
