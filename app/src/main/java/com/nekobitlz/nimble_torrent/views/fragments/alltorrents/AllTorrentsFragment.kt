package com.nekobitlz.nimble_torrent.views.fragments.alltorrents

import androidx.fragment.app.Fragment
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragment

class AllTorrentsFragment : BaseFragment() {

    companion object {

        fun newInstance(): Fragment {
            val allTorrentsFragment = AllTorrentsFragment()

            return allTorrentsFragment
        }
    }
}