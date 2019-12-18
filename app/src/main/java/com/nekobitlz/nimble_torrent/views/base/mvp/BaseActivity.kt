package com.nekobitlz.nimble_torrent.views.base.mvp

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(), BaseContract.View {

    override fun showSuccess(stringId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showInfo(stringId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(stringId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLoading(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}