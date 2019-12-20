package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.os.Bundle
import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.nekobitlz.nimble_torrent.repository.ITorrentRepository
import com.nekobitlz.nimble_torrent.views.base.mvp.BasePresenter
import java.lang.Exception

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val streamUrl =
        "magnet:?xt=urn:btih:88594aaacbde40ef3e2510c47374ec0aa396c08e&dn=bbb%5Fsunflower%5F1080p%5F30fps%5Fnormal.mp4&tr=udp%3A%2F%2Ftracker.openbittorrent.com%3A80%2Fannounce&tr=udp%3A%2F%2Ftracker.publicbt.com%3A80%2Fannounce&ws=http%3A%2F%2Fdistribution.bbb3d.renderfarming.net%2Fvideo%2Fmp4%2Fbbb%5Fsunflower%5F1080p%5F30fps%5Fnormal.mp4"

    override fun onButtonClick() {
        torrentRepository.setListener(this)
        torrentRepository.startStream(streamUrl)
        view.showToast("Clicked")
    }

    /*override fun onViewClicked(torrentFile: TorrentFile, action: ClickType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }*/

}