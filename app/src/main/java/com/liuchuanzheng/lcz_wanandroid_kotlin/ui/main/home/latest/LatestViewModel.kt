package com.xiaojianjun.wanandroid.ui.main.home.latest

import androidx.lifecycle.MutableLiveData
import com.liuchuanzheng.lcz_wanandroid_kotlin.model.bean.Article
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseViewModel
import com.xiaojianjun.wanandroid.common.loadmore.LoadMoreStatus

class LatestViewModel : BaseViewModel() {

    companion object {
        const val INITIAL_PAGE = 0
    }

    private val latestRepository by lazy { LatestRepository() }

    val articleList = MutableLiveData<MutableList<Article>>(mutableListOf())

    val loadMoreStatus = MutableLiveData<LoadMoreStatus>()
    val refreshStatus = MutableLiveData<Boolean>()
    val reloadStatus = MutableLiveData<Boolean>()

    private var page = INITIAL_PAGE

    fun refreshProjectList() {
        refreshStatus.value = true
        reloadStatus.value = false
        launch(
            workBlock = {
                val pagination = latestRepository.getProjectList(INITIAL_PAGE)
                page = pagination.curPage
                articleList.value = pagination.datas.toMutableList()
                refreshStatus.value = false
            },
            errorBlock = {
                refreshStatus.value = false
                reloadStatus.value = page == INITIAL_PAGE
            }
        )
    }

    fun loadMoreProjectList() {
        loadMoreStatus.value = LoadMoreStatus.LOADING
        launch(
            workBlock = {
                val pagination = latestRepository.getProjectList(page)
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