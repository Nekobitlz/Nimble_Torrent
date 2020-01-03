package com.nekobitlz.nimble_torrent.views.fragments.alltorrents

import android.os.Bundle
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseContract

interface AllTorrentsContract {

    interface View: BaseContract.View {
        fun showToast(text: String)
        fun setupView(torrentFiles: List<TorrentData>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun onSetup(arguments: Bundle?)
        fun onRefresh()
        fun onDeleteClick(torrentData: TorrentData)
    }
}