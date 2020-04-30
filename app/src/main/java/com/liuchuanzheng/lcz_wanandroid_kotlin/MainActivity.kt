package com.liuchuanzheng.lcz_wanandroid_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseActivity


/**
 * 首页：热门文章、最新项目、广场、项目分类、公众号
 * 体系：体系
 * 发现：Banner、搜索热词、常用网站
 * 导航：导航
 * 我的：登录、注册、积分排行、我的积分、我的分享、稍后阅读、我的收藏、浏览历史、关于作者、开源项目、系统设置
 *
 * 详情：文章详情（收藏、分享、浏览器打开、复制链接、刷新页面）
 *
 * 搜索：搜索历史、热门搜索
 *
 * 设置：日夜间模式、调整亮度、字体大小、清除缓存、检查版本、关于玩安卓、退出登录
 */
class MainActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
