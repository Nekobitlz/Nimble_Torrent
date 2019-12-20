package com.nekobitlz.nimble_torrent.views.main

import android.os.Bundle
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseActivity
import com.nekobitlz.nimble_torrent.views.main.di.DaggerMainComponent
import com.nekobitlz.nimble_torrent.views.main.di.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private val pagerAdapter = MainPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder().build().inject(this)

        setSupportActionBar(toolbar_main)
        supportActionBar?.title = getString(R.string.app_name)

        presenter.attachView(this)

        vp_main.adapter = pagerAdapter
        stl_main.setViewPager(vp_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}