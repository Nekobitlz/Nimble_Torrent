package com.nekobitlz.nimble_torrent.views.dialogs

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.nekobitlz.nimble_torrent.R

class DialogManager : IDialogManager {

    override fun showAddMagnetDialog(context: Context, func: (text: String) -> Unit) {
        MaterialDialog(context).title(R.string.dialog_add_magnet_title).show {
            input { _, text -> func(text.toString()) }
            positiveButton(R.string.submit)
        }
    }
}