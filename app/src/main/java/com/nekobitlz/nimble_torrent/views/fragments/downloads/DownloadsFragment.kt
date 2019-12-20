package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.TorrentOptions
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragment
import com.nekobitlz.nimble_torrent.views.fragments.downloads.di.DaggerDownloadsComponent
import kotlinx.android.synthetic.main.fragment_downloads.*
import java.lang.Exception
import javax.inject.Inject

class DownloadsFragment : BaseFragment(), DownloadsContract.View {
    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    @Inject
    lateinit var presenter: DownloadsContract.Presenter

   // private val downloadsAdapter = DownloadsAdapter()

    companion object {

        fun newInstance(): Fragment {
            val downloadsFragment = DownloadsFragment()

            return downloadsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDownloadsComponent.builder().build().inject(this)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_downloads, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       // presenter.onSetup(arguments)
        super.onViewCreated(view, savedInstanceState)

     //   presenter.onRefresh()
        btn_start.setOnClickListener {
            presenter.onButtonClick()
        }

        /*rv_downloads.apply {
            adapter = downloadsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        srl_downloads.apply {
            isRefreshing = true
            setOnRefreshListener { presenter.onRefresh() }
        }*/
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this.context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.activity!!,  Array<String>(2) {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0)
        }
    }
}