package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nekobitlz.nimble_torrent.NimbleApplication
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragment
import com.nekobitlz.nimble_torrent.views.dialogs.IDialogManager
import com.nekobitlz.nimble_torrent.views.fragments.downloads.di.DaggerDownloadsComponent
import kotlinx.android.synthetic.main.fragment_downloads.*
import javax.inject.Inject

class DownloadsFragment : BaseFragment(), DownloadsContract.View {

    @Inject
    lateinit var presenter: DownloadsContract.Presenter

    @Inject
    lateinit var dialogManager: IDialogManager

    private val onDeleteClick: (View, Int) -> Unit = { _, position ->
        val torrent = downloadsAdapter.torrentFiles[position]
        dialogManager.showDeleteTorrentDialog(context!!, torrent) {
            presenter.onDeleteClick(torrent)
        }
    }

    private val downloadsAdapter = DownloadsAdapter(onDeleteClick)

    companion object {
        fun newInstance(): Fragment = DownloadsFragment()
    }

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerDownloadsComponent
            .builder()
            .appComponent((activity!!.application as NimbleApplication).appComponent)
            .build()
            .inject(this)

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
        presenter.onSetup(arguments)
        super.onViewCreated(view, savedInstanceState)

        presenter.onRefresh()

        rv_downloads.apply {
            adapter = downloadsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        srl_downloads.apply {
            isRefreshing = true
            setColorSchemeResources(R.color.colorRedMain)
            setOnRefreshListener { presenter.onRefresh() }
        }
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(
                this.context!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this.activity!!,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        }
    }

    override fun setupView(torrentFiles: List<TorrentData>) {
        if (!isVisible || !isAdded) {
            return
        }

        srl_downloads.isRefreshing = false

        if (torrentFiles.isEmpty()) {
            tv_no_torrents.visibility = View.VISIBLE
            rv_downloads.visibility = View.GONE
        } else {
            tv_no_torrents.visibility = View.GONE
            rv_downloads.visibility = View.VISIBLE
            downloadsAdapter.torrentFiles = torrentFiles
        }
        downloadsAdapter.notifyDataSetChanged()
    }

    override fun setLoading(isLoading: Boolean) {
        srl_downloads.isRefreshing = isLoading
    }
}