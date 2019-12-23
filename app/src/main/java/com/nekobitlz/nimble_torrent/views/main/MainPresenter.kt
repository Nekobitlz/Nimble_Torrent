package com.nekobitlz.nimble_torrent.views.main

import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.views.base.mvp.BasePresenter

class MainPresenter(private val torrentRepository: ITorrentRepository) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onFabClicked() {
        view.showAddMagnetDialog()
    }

    override fun onMagnetLinkEntered(link: String) {
        torrentRepository.addMagnetLink(link)
    }

}