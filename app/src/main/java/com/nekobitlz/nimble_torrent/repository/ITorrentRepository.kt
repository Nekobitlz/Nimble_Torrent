package com.nekobitlz.nimble_torrent.repository

import com.github.se_bastiaan.torrentstream.listeners.TorrentListener

interface ITorrentRepository {
    fun startStream(url: String)
    fun setListener(listener: TorrentListener)
}