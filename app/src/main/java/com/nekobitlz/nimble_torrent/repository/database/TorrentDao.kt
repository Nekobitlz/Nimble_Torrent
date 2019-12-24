package com.nekobitlz.nimble_torrent.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface TorrentDao {
    @Query("SELECT * from torrentdata")
    fun getAll(): Flowable<List<TorrentData>>

    @Query("SELECT * from torrentdata WHERE name =:name")
    fun getTorrent(name: String): Flowable<TorrentData>

    @Query("UPDATE torrentdata SET progress =:progress WHERE name =:name")
    fun updateTorrent(name: String, progress: Float)

    @Insert
    fun insertAll(vararg torrents: TorrentData)

    @Delete
    fun delete(torrentData: TorrentData)
}