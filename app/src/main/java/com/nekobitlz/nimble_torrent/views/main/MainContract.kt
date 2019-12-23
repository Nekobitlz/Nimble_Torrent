package com.nekobitlz.nimble_torrent.views.main

import com.nekobitlz.nimble_torrent.views.base.mvp.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun showAddMagnetDialog()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onFabClicked()
        fun onMagnetLinkEntered(link: String)
    }
}