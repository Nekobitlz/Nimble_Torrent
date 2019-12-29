package com.nekobitlz.nimble_torrent.views.dialogs

import android.content.Context
import com.nekobitlz.nimble_torrent.repository.database.TorrentData

interface IDialogManager {

    fun showAddMagnetDialog(context: Context, func: (text: String) -> Unit)
    fun showDeleteTorrentDialog(context: Context, torrentData: TorrentData, func: () -> Unit)
}