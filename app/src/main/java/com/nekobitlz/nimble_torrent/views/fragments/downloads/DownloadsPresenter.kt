package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.os.Bundle
import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import com.nekobitlz.nimble_torrent.views.base.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DownloadsPresenter(private val torrentRepository: ITorrentRepository) :
    BasePresenter<DownloadsContract.View>(), DownloadsContract.Presenter {

    override fun onSetup(arguments: Bundle?) {
        torrentRepository.getDownloadingFiles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseSubscriber<List<TorrentData>>(false) {
                override fun onNext(t: List<TorrentData>) {
                    view.setupView(t)
                }
            })
            .addSubscription()
    }

    override fun onRefresh() {
        torrentRepository.getDownloadingFiles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : BaseSubscriber<List<TorrentData>>() {
                override fun onNext(t: List<TorrentData>) {
                    view.setupView(t)
                }
            })
            .addSubscription()
    }

    override fun onDeleteClick(torrentData: TorrentData) {
        torrentRepository.deleteTorrent(torrentData)
    }
}
