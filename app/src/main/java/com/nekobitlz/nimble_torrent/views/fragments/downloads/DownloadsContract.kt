package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.os.Bundle
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseContract

interface DownloadsContract {

    interface View: BaseContract.View {
        fun showToast(text: String)
        fun setupView(torrentFiles: List<TorrentData>)
    //    fun updateTorrentView(torrentFile: TorrentStatus)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun onSetup(arguments: Bundle?)
        fun onRefresh()
        fun onDeleteClick(torrentData: TorrentData)
        //   fun onViewClicked(torrentFile: TorrentFile, action: ClickType)
    }
}