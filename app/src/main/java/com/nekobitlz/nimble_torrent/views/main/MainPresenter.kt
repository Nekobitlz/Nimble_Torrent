package com.nekobitlz.nimble_torrent.views.main

import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.views.base.mvp.BasePresenter

class MainPresenter(private val torrentRepository: ITorrentRepository) : BasePresenter<MainContract.View>(), MainContract.Presenter, TorrentListener {

    override fun onFabClicked() {
        view.showAddMagnetDialog()
    }

    override fun onMagnetLinkEntered(link: String) {
        torrentRepository.addMagnetLink(link, this)
    }

    override fun onStreamReady(torrent: Torrent) {
        view.showToast("Ready")
    }

    override fun onStreamPrepared(torrent: Torrent) {
        view.showToast("Prepared")
    }

    override fun onStreamStopped() {
        view.showToast("Stopped")
    }

    override fun onStreamStarted(torrent: Torrent?) {
        view.showToast("Started")
    }

    override fun onStreamProgress(torrent: Torrent?, status: StreamStatus?) {
        view.showToast(status!!.progress.toString() + " Percents downloaded")
    }

    override fun onStreamError(torrent: Torrent?, e: Exception?) {
        view.showToast(e.toString())
    }

}