package com.nekobitlz.nimble_torrent.repository

import android.os.Environment
import com.github.se_bastiaan.torrentstream.TorrentOptions
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener

class TorrentStreamRepository : ITorrentStreamRepository {

    private var torrentStream: TorrentStream
    private lateinit var listener: TorrentListener

    init {
        val torrentOptions = TorrentOptions.Builder()
            .saveLocation(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
            .removeFilesAfterStop(true)
            .build()

        torrentStream = TorrentStream.init(torrentOptions)
    }

    override fun getStream(): TorrentStream = torrentStream

    override fun setListener(listener: TorrentListener) {
        this.listener = listener
        torrentStream.addListener(listener)
    }

    override fun startStream(url: String) {
        torrentStream.startStream(url)
    }

    override fun stopStream() {
        torrentStream.stopStream()
    }

    override fun pauseStream() {
        torrentStream.pauseSession()
    }

    override fun resumeStream() {
        torrentStream.resumeSession()
    }

    override fun isStreaming(): Boolean = torrentStream.isStreaming
}
