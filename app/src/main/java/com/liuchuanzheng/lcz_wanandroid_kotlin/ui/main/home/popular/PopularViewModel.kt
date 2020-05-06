package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.popular

import androidx.lifecycle.MutableLiveData
import com.liuchuanzheng.lcz_wanandroid_kotlin.model.bean.Article
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseViewModel
import com.xiaojianjun.wanandroid.common.loadmore.LoadMoreStatus

/**
 * @author 刘传政
 * @date 2020/5/6 14:56
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class PopularViewModel : BaseViewModel() {
    companion object {
        const val INITIAL_PAGE = 0
    }

    private val popularRepository by lazy { PopularRepository() }

    val articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()
    val loadMoreStatus = MutableLiveData<LoadMoreStatus>()
    val refreshStatus = MutableLiveData<Boolean>()
    val reloadStatus = MutableLiveData<Boolean>()

    private var page = INITIAL_PAGE

    fun refreshArticleList() {
        refreshStatus.value = true
        reloadStatus.value = false
        launch(
            workBlock = {
                val topArticleListDefferd = async {
                    popularRepository.getTopArticleList()
                }
                val paginationDefferd = async {
                    popularRepository.getArticleList(INITIAL_PAGE)
                }
                val topArticleList = topArticleListDefferd.await()
                    .apply { forEach { it.top = true } }
                val pagination = paginationDefferd.await()
                page = pagination.curPage
                articleList.value = mutableListOf<Article>().apply {
                    addAll(topArticleList)
                    addAll(pagination.datas)
                }
                refreshStatus.value = false
            },
            errorBlock = {
                refreshStatus.value = false
                reloadStatus.value = page == INITIAL_PAGE
            }
        )
    }

    fun loadMoreArticleList() {
        loadMoreStatus.value = LoadMoreStatus.LOADING
        launch(
            workBlock = {
                val pagination = popularRepository.getArticleList(page)
                page = pagination.curPage
                val currentList = articleList.value ?: mutableListOf()
                currentList.addAll(pagination.datas)
                articleList.value = currentList
                loadMoreStatus.value = if (pagination.offset >= pagination.total) {
                    LoadMoreStatus.END
                } else {
                    LoadMoreStatus.COMPLETED
                }
            },
            errorBlock = {
                loadMoreStatus.value = LoadMoreStatus.ERROR
            }
        )
    }

}