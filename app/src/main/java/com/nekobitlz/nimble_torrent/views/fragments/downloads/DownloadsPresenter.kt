package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.os.Bundle
import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import com.nekobitlz.nimble_torrent.views.base.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class DownloadsPresenter(private val torrentRepository: ITorrentRepository) :
    BasePresenter<DownloadsContract.View>(), DownloadsContract.Presenter, TorrentListener {

    override fun onStreamReady(torrent: Torrent) {
        view.showToast("Ready")
    }

    override fun onStreamPrepared(torrent: Torrent) {
        view.showToast("Prepared")
    }

    override fun onStreamStopped() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStreamStarted(torrent: Torrent?) {
        view.showToast("Started")
    }

    override fun onStreamProgress(torrent: Torrent?, status: StreamStatus?) {
        view.showToast(status.toString())
    }

    override fun onStreamError(torrent: Torrent?, e: Exception?) {
        view.showToast(e.toString())
    }

    override fun onSetup(arguments: Bundle?) {
        torrentRepository.getDownloadingFiles()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseSubscriber<List<TorrentData>>(false) {
                override fun onNext(t: List<TorrentData>) {
                    view.setupView(t)
                }
            })
            .addSubscription()

        //TODO() : Add file delete listener
    }

    override fun onRefresh() {
        torrentRepository.getDownloadingFiles()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseSubscriber<List<TorrentData>>() {
                override fun onNext(t: List<TorrentData>) {
                    view.setupView(t)
                }
            })
            .addSubscription()
    }

    /*override fun onViewClicked(torrentFile: TorrentFile, action: ClickType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/

}