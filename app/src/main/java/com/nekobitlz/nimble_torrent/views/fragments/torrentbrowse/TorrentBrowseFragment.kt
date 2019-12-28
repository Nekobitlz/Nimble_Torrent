package com.nekobitlz.nimble_torrent.views.fragments.torrentbrowse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragment

class TorrentBrowseFragment : BaseFragment() {

    companion object {

        fun newInstance(): Fragment {
            val torrentBrowseFragment = TorrentBrowseFragment()

            return torrentBrowseFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_browse, container, false)
}