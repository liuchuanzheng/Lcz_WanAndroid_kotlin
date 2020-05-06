package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import com.liuchuanzheng.lcz_wanandroid_kotlin.ext.showToast
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseActivity
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.discovery.DiscoveryFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.HomeFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.mine.MineFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.navigation.NavigationFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.system.SystemFragment
import kotlinx.android.synthetic.main.activity_main.*


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
    private var previoisTimeMillis = 0L
    private lateinit var fragments: Map<Int, Fragment>
    override fun layoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragments = mapOf(
            R.id.home to createFragment(HomeFragment::class.java),
            R.id.system to createFragment(SystemFragment::class.java),
            R.id.discovery to createFragment(DiscoveryFragment::class.java),
            R.id.navigation to createFragment(NavigationFragment::class.java),
            R.id.mine to createFragment(MineFragment::class.java)
        )
        bottomNavigationView.run {
            setOnNavigationItemSelectedListener { menuItem ->
                showFragment(menuItem.itemId)
                true
            }
        }
        if (savedInstanceState == null) {
            //选中第一个.否则选中监听不触发任何一个
            val initialItemId = R.id.home
            bottomNavigationView.selectedItemId = initialItemId
//            showFragment(initialItemId)
        }
    }

    override fun onBackPressed() {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - previoisTimeMillis < 2000) {
            super.onBackPressed()
        } else {
            showToast(R.string.press_again_to_exit)
            previoisTimeMillis = currentTimeMillis
        }

    }

    private fun createFragment(clazz: Class<out Fragment>): Fragment {
        var fragment = supportFragmentManager.fragments.find {
            it.javaClass == clazz
        }
        if (fragment == null) {
            fragment = when (clazz) {
                HomeFragment::class.java -> HomeFragment.newInstance()
                SystemFragment::class.java -> SystemFragment.newInstance()
                DiscoveryFragment::class.java -> DiscoveryFragment.newInstance()
                NavigationFragment::class.java -> NavigationFragment.newInstance()
                MineFragment::class.java -> MineFragment.newInstance()
                else -> throw IllegalArgumentException("argument ${clazz.simpleName} is illegal")
            }
        }
        return fragment
    }

    private fun showFragment(menuItemId: Int) {
        val currentFragment = supportFragmentManager.fragments.find {
            it.isVisible && it in fragments.values
        }
        val targetFragment = fragments.entries.find { it.key == menuItemId }?.value
        supportFragmentManager.beginTransaction().apply {
            currentFragment?.let { if (it.isVisible) hide(it) }
            targetFragment?.let {
                if (it.isAdded) show(it) else add(R.id.fl, it)
            }
        }.commit()
    }
}
