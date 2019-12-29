package com.nekobitlz.nimble_torrent.views.dialogs

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.repository.database.TorrentData

class DialogManager : IDialogManager {
    override fun showAddMagnetDialog(context: Context, func: (text: String) -> Unit) {
        MaterialDialog(context)
            .title(R.string.dialog_add_magnet_title)
            .show {
                input { _, text -> func(text.toString()) }
                positiveButton(R.string.dialog_submit)
                negativeButton(R.string.dialog_decline)
            }
    }

    override fun showDeleteTorrentDialog(context: Context, torrentData: TorrentData, func: () -> Unit) {
        MaterialDialog(context)
            .title(R.string.dialog_delete_torrent_title, "Are you sure you want to delete ${torrentData.name}?")
            .show {
                positiveButton(R.string.delete, "Delete") { func() }
                negativeButton(R.string.dialog_decline)
            }
    }
}