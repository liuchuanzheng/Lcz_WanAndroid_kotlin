package com.liuchuanzheng.lcz_wanandroid_kotlin.model.api

import android.util.Log
import com.orhanobut.logger.Logger
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author 刘传政
 * @date 2020/5/6 17:54
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class HttpLogger : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        var msg = message
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((msg.startsWith("{") && msg.endsWith("}"))
            || (msg.startsWith("[") && msg.endsWith("]"))
        ) {
//            msg = JsonUtils.formatJson(JsonUtils.decodeUnicode(msg))
            Log.i("玩Android", msg)
            Logger.json(msg)
        } else {
            Log.i("玩Android", msg)
        }
    }

}