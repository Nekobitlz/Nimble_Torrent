package com.nekobitlz.nimble_torrent.views.dialogs

import android.content.Context

interface IDialogManager {

    fun showAddMagnetDialog(context: Context, func: (text: String) -> Unit)
}