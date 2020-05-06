package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.popular

import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseVMFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.ArticleAdapter
import com.xiaojianjun.wanandroid.common.loadmore.CommonLoadMoreView
import com.xiaojianjun.wanandroid.common.loadmore.LoadMoreStatus
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.include_reload.*

/**
 * @author 刘传政
 * @date 2020/5/6 14:55
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class PopularFragment : BaseVMFragment<PopularViewModel>() {
    companion object {
        fun newInstance() = PopularFragment()
    }

    private lateinit var mAdapter: ArticleAdapter

    override fun layoutRes(): Int {
        return R.layout.fragment_popular
    }

    override fun viewModelClass(): Class<PopularViewModel> {
        return PopularViewModel::class.java
    }

    override fun initView() {
        super.initView()
        swipeRefreshLayout.run {
            setColorSchemeResources(R.color.textColorPrimary)
            setProgressBackgroundColorSchemeResource(R.color.colorAccent)
            setOnRefreshListener {
                mViewModel.refreshArticleList()
            }
        }
        mAdapter = ArticleAdapter(R.layout.item_article).apply {
            setLoadMoreView(CommonLoadMoreView())
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener({
                mViewModel.loadMoreArticleList()
            }, recyclerView)
            setOnItemClickListener { adapter, view, position ->

            }
            setOnItemChildClickListener { adapter, view, position ->

            }
            btnReload.setOnClickListener {
                mViewModel.refreshArticleList()
            }
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

    override fun initData() {
        super.initData()
    }

    override fun lazyLoadData() {
        mViewModel.refreshArticleList()
    }

}