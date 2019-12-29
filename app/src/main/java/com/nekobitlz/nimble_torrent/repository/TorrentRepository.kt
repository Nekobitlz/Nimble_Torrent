package com.nekobitlz.nimble_torrent.repository

import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.nekobitlz.nimble_torrent.repository.database.TorrentDao
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import io.reactivex.Flowable
import javax.inject.Inject

class TorrentRepository @Inject constructor(private val dao: TorrentDao) : ITorrentRepository, TorrentListener {

    override fun getDownloadingFiles(): Flowable<List<TorrentData>> = dao.getAll()

    override fun deleteTorrent(torrent: TorrentData) {
        Observable.fromCallable { dao.delete(torrent) }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun addMagnetLink(link: String, listener: TorrentListener) {
        TorrentStreamRepository().getStream().also {
            it.startStream(link)
            it.addListener(listener)
            it.addListener(this)
        }
    }

    override fun onStreamPrepared(torrent: Torrent) {
        val torrentHandle = torrent.torrentHandle
        val torrentData = TorrentData(0, torrentHandle.name(), torrentHandle.savePath(), torrentHandle.status().total(), 0f)
        dao.insertAll(torrentData)
    }

    override fun onStreamProgress(torrent: Torrent, status: StreamStatus) {
        val torrentHandle = torrent.torrentHandle

        if (torrentHandle.status().isFinished) {
            dao.updateTorrent(torrentHandle.name(), 100f, 0.0f, 0, 0)
        } else {
            dao.updateTorrent(torrentHandle.name(), status.progress, status.downloadSpeed, status.seeds, torrentHandle.peerInfo().size)
        }
    }

    override fun onStreamReady(torrent: Torrent) { /* not implemented */ }

    override fun onStreamStopped() { /* not implemented */ }

    override fun onStreamStarted(torrent: Torrent) { /* not implemented */ }

    override fun onStreamError(torrent: Torrent, e: Exception) { /* not implemented */ }
}