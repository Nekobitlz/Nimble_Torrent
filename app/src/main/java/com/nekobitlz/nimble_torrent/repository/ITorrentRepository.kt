package com.nekobitlz.nimble_torrent.repository

import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import io.reactivex.Flowable

interface ITorrentRepository {

    fun getDownloadingFiles(): Flowable<List<TorrentData>>
    fun addMagnetLink(link: String)
}