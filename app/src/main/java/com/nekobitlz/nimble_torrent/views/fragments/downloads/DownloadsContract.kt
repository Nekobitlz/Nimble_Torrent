package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.os.Bundle
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseContract

interface DownloadsContract {

    interface View: BaseContract.View {
        fun showToast(text: String)
   /*     fun setupView(torrentFiles: List<TorrentFile>)
        fun updateTorrentView(torrentFile: TorrentFile, action: ClickType)
    */}

    interface Presenter: BaseContract.Presenter<View> {
        fun onSetup(arguments: Bundle?)
        fun onRefresh()
        fun onButtonClick()
     //   fun onViewClicked(torrentFile: TorrentFile, action: ClickType)
    }
}