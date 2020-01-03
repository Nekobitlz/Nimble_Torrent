package com.nekobitlz.nimble_torrent.views.main

import android.os.Bundle
import android.widget.Toast
import com.nekobitlz.nimble_torrent.NimbleApplication
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseActivity
import com.nekobitlz.nimble_torrent.views.dialogs.IDialogManager
import com.nekobitlz.nimble_torrent.views.main.di.DaggerMainComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var dialogManager: IDialogManager

    private val pagerAdapter = MainPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent
            .builder()
            .appComponent((application as NimbleApplication).appComponent)
            .build()
            .inject(this)

        setSupportActionBar(toolbar_main)
        supportActionBar?.title = getString(R.string.app_name)

        presenter.attachView(this)

        vp_main.adapter = pagerAdapter
        stl_main.setViewPager(vp_main)
        fab.setOnClickListener { presenter.onFabClicked() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showAddMagnetDialog() = dialogManager.showAddMagnetDialog(this) { link ->
        presenter.onMagnetLinkEntered(link)
        (vp_main.adapter as MainPagerAdapter).setLoading(true)
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}