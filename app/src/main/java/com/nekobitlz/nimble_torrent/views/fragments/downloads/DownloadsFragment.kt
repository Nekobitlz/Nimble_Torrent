package com.nekobitlz.nimble_torrent.views.fragments.downloads

import androidx.fragment.app.Fragment
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragment

class DownloadsFragment : BaseFragment() {

    companion object {

        fun newInstance(): Fragment {
            val downloadsFragment = DownloadsFragment()

            return downloadsFragment
        }
    }

}