package com.nekobitlz.nimble_torrent.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TorrentData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val filePath: String,
    val size: Long,
    var progress: Float = 0.0f,
    var seeds: Int = 0,
    var peers: Int = 0,
    var speed: Float = 0.0f,
    var isFinished: Boolean = false
)