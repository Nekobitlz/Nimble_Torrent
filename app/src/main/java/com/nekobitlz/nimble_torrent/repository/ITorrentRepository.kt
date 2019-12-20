package com.nekobitlz.nimble_torrent.repository

import com.github.se_bastiaan.torrentstream.listeners.TorrentListener

interface ITorrentRepository {
    fun startStream(url: String)
    fun stopStream()
    fun pauseStream()
    fun resumeStream()
    fun isStreaming(): Boolean
    fun setListener(listener: TorrentListener)
}