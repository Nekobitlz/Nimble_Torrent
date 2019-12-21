package com.nekobitlz.nimble_torrent.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TorrentData::class], version = 1)
abstract class TorrentDatabase : RoomDatabase() {
    abstract fun torrentDao(): TorrentDao
}