package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*

/**
 * @author 刘传政
 * @date 2020/5/6 14:23
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
//typealias 不会生成新的类型，编译器只做简单内联替换
typealias WorkBlock<T> = suspend () -> T
typealias  ErrorBlock = suspend (e: Exception) -> Unit
typealias  CancelBlock = suspend (e: Exception) -> Unit

open class BaseViewModel : ViewModel() {
    protected val userRepository by lazy { }
    val loginStatusInvalid: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * 创建并执行协程
     * @param workBlock 协程中执行
     * @param errorBlock 错误时执行
     * @return Job
     */
    protected fun launch(
        workBlock: WorkBlock<Unit>,
        errorBlock: ErrorBlock? = null,
        cancelBlock: CancelBlock? = null
    ): Job {
        return viewModelScope.launch {
            try {
                workBlock.invoke()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancelBlock?.invoke(e)
                    }
                    else -> {
                        Logger.i("errorBlock: ${e}")
                        errorBlock?.invoke(e)
                    }
                }
            }
        }
    }

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @return Deferred<T>
     */
    protected fun <T> async(block: WorkBlock<T>): Deferred<T> {
        return viewModelScope.async { block.invoke() }
    }

    /**
     * 取消协程
     * @param job 协程job
     */
    protected fun cancelJob(job: Job?) {
        if (job != null && job.isActive && !job.isCompleted && !job.isCancelled) {
            job.cancel()
        }
    }

}