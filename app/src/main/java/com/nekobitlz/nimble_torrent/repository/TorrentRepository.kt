package com.nekobitlz.nimble_torrent.repository

import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.nekobitlz.nimble_torrent.repository.database.TorrentDao
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TorrentRepository @Inject constructor(private val dao: TorrentDao) : ITorrentRepository, TorrentListener {

    override fun getDownloadingFiles(): Flowable<List<TorrentData>> = dao.getAllDownloads(isFinished = false)

    override fun getAllFiles(): Flowable<List<TorrentData>> = dao.getAll()

    override fun deleteTorrent(torrent: TorrentData) {
        Observable.fromCallable { dao.delete(torrent) }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun addMagnetLink(link: String, listener: TorrentListener) {
        val torrentStreamRepository = TorrentStreamRepository()

        torrentStreamRepository.apply {
            setListener(this@TorrentRepository)
            setListener(listener)
            startStream(link)
        }
    }

    override fun onStreamPrepared(torrent: Torrent) {
        val torrentHandle = torrent.torrentHandle
        val torrentData = TorrentData(0, torrentHandle.name(), torrentHandle.savePath(), torrentHandle.status().total(), 0f)

        Observable.fromCallable { dao.insertAll(torrentData) }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun onStreamProgress(torrent: Torrent, status: StreamStatus) {
        val torrentHandle = torrent.torrentHandle

        Observable.fromCallable {
            dao.updateTorrent(
                torrentHandle.name(),
                status.progress,
                status.downloadSpeed.toFloat(),
                status.seeds,
                torrentHandle.peerInfo().size
            )
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun onStreamReady(torrent: Torrent?) { /* not implemented */ }

    override fun onStreamStopped() { /* not implemented */ }

    override fun onStreamStarted(torrent: Torrent?) { /* not implemented */ }

    override fun onStreamError(torrent: Torrent?, e: Exception) { e.printStackTrace() }

    override fun onStreamFinished(torrent: Torrent) {
        val torrentHandle = torrent.torrentHandle

        Observable.fromCallable {
            dao.finishTorrentDownload(torrentHandle.name(), true)
            dao.updateTorrent(torrentHandle.name(), 100.0f, 0f, 0, 0)
        }.subscribeOn(Schedulers.io())
            .subscribe()

        //TODO : Change speed from float to int
    }
}