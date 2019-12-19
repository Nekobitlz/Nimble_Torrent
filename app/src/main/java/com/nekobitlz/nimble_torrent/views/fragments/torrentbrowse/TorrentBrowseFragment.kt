package com.nekobitlz.nimble_torrent.views.fragments.torrentbrowse

import androidx.fragment.app.Fragment
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragment

class TorrentBrowseFragment : BaseFragment() {

    companion object {

        fun newInstance(): Fragment {
            val torrentBrowseFragment = TorrentBrowseFragment()

            return torrentBrowseFragment
        }
    }
}