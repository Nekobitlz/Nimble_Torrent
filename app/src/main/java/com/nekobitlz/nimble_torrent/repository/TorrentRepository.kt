package com.nekobitlz.nimble_torrent.repository

import com.nekobitlz.nimble_torrent.repository.database.TorrentDao
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import io.reactivex.Flowable
import javax.inject.Inject

class TorrentRepository @Inject constructor(private val dao: TorrentDao) : ITorrentRepository {

    override fun getDownloadingFiles(): Flowable<List<TorrentData>> {
        //return dao.getAll()
        val torrentData = listOf(TorrentData(128, "TEST", "TEST", "17KB", "OFF"))
        return Flowable.just(torrentData)
    }

    override fun addMagnetLink(link: String) {
        // Pass torrent to stream repo
        // get torrent from her
        // add torrent to dao
    }
}