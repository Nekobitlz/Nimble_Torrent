package com.nekobitlz.nimble_torrent.views.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragmentStatePagerAdapter
import com.nekobitlz.nimble_torrent.views.fragments.alltorrents.AllTorrentsFragment
import com.nekobitlz.nimble_torrent.views.fragments.downloads.DownloadsFragment
import com.nekobitlz.nimble_torrent.views.fragments.torrentbrowse.TorrentBrowseFragment
import java.lang.IllegalStateException

class MainPagerAdapter(fragmentManager: FragmentManager) :
    BaseFragmentStatePagerAdapter(fragmentManager) {

    private val browseFragment = TorrentBrowseFragment.newInstance()
    private val allTorrentsFragment = AllTorrentsFragment.newInstance()
    private val downloadsFragment = DownloadsFragment.newInstance()

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> browseFragment
        1 -> allTorrentsFragment
        2 -> downloadsFragment
        else -> throw IllegalStateException("No more that 2 fragments required")
    }

    override fun getCount(): Int {
        return 3
    }

    // TODO Extract String resources
    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Browse"
        1 -> "My torrents"
        2 -> "Downloads"
        else -> throw IllegalStateException("No more that 2 fragments required")
    }
}