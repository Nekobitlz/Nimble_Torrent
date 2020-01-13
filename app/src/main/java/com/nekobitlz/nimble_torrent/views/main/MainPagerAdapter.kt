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
        ALL_TORRENTS_FRAGMENT -> allTorrentsFragment
        DOWNLOADS_FRAGMENT -> downloadsFragment
        BROWSE_FRAGMENT -> browseFragment
        else -> throw IllegalStateException("No more that 2 fragments required")
    }

    override fun getCount(): Int {
        return 3
    }

    // TODO Extract String resources
    override fun getPageTitle(position: Int): CharSequence = when (position) {
        ALL_TORRENTS_FRAGMENT -> "My torrents"
        DOWNLOADS_FRAGMENT -> "Downloads"
        BROWSE_FRAGMENT -> "Browse"
        else -> throw IllegalStateException("No more that 2 fragments required")
    }

    fun setLoading(isLoading: Boolean) {
        (downloadsFragment as DownloadsFragment).setLoading(isLoading)
    }

    companion object {
        const val ALL_TORRENTS_FRAGMENT = 0
        const val DOWNLOADS_FRAGMENT = 1
        const val BROWSE_FRAGMENT = 2
    }
}