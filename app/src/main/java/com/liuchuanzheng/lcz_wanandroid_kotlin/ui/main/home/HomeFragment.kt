package com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.liuchuanzheng.lcz_wanandroid_kotlin.R
import com.liuchuanzheng.lcz_wanandroid_kotlin.common.ScrollToTop
import com.liuchuanzheng.lcz_wanandroid_kotlin.common.SimpleFragmentPagerAdapter
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.base.BaseFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.latest.LatestFragment
import com.liuchuanzheng.lcz_wanandroid_kotlin.ui.main.home.popular.PopularFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author 刘传政
 * @date 2020/5/6 10:23
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
class HomeFragment : BaseFragment(), ScrollToTop {
    private lateinit var fragments: List<Fragment>
    override fun layoutRes(): Int {
        return R.layout.fragment_home
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragments = listOf(
            PopularFragment.newInstance(),
            LatestFragment.newInstance(),
            PopularFragment.newInstance(),
            PopularFragment.newInstance(),
            PopularFragment.newInstance()
        )
        val titles = listOf<CharSequence>(
            getString(R.string.popular_articles),
            getString(R.string.latest_project),
            getString(R.string.plaza),
            getString(R.string.project_category),
            getString(R.string.wechat_public)
        )
        viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager, fragments, titles)
        viewPager.offscreenPageLimit = fragments.size
        tabLayout.setupWithViewPager(viewPager)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, offset ->

        })
        llSearch.setOnClickListener {

        }

    }

    override fun scrollToTop() {
        if (!this::fragments.isInitialized) return
        val currentFragment = fragments[viewPager.currentItem]
        if (currentFragment is ScrollToTop && currentFragment.isVisible) {
            currentFragment.scrollToTop()
        }
    }
}