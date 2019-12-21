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

    @Insert
    fun insertAll(vararg torrents: TorrentData)

    @Delete
    fun delete(torrentData: TorrentData)
}