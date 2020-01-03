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

    @Query("SELECT * from torrentdata WHERE isFinished =:isFinished")
    fun getAllDownloads(isFinished: Boolean): Flowable<List<TorrentData>>

    @Query("SELECT * from torrentdata WHERE name =:name")
    fun getTorrent(name: String): Flowable<TorrentData>

    @Query("UPDATE torrentdata SET progress =:progress, speed=:speed, seeds =:seeds, peers =:peers WHERE name =:name")
    fun updateTorrent(name: String, progress: Float, speed: Float, seeds: Int, peers: Int)

    @Query("UPDATE torrentdata SET isFinished =:isFinished WHERE name =:name")
    fun finishTorrentDownload(name: String, isFinished: Boolean)

    @Insert
    fun insertAll(vararg torrents: TorrentData)

    @Delete
    fun delete(torrentData: TorrentData)
}