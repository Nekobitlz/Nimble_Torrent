package com.nekobitlz.nimble_torrent.views.fragments.alltorrents

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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nekobitlz.nimble_torrent.NimbleApplication
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import com.nekobitlz.nimble_torrent.views.base.mvp.BaseFragment
import com.nekobitlz.nimble_torrent.views.dialogs.IDialogManager
import com.nekobitlz.nimble_torrent.views.fragments.alltorrents.di.DaggerAllTorrentsComponent
import com.nekobitlz.nimble_torrent.views.main.MainActivity
import kotlinx.android.synthetic.main.fragment_all.*
import javax.inject.Inject

class AllTorrentsFragment : BaseFragment(), AllTorrentsContract.View {

    companion object {
        fun newInstance(): Fragment = AllTorrentsFragment()
    }

    @Inject
    lateinit var presenter: AllTorrentsContract.Presenter

    @Inject
    lateinit var dialogManager: IDialogManager

    private val onDeleteClick: (View, Int) -> Unit = { _, position ->
        val torrent = allTorrentsAdapter.torrentFiles[position]

        dialogManager.showDeleteTorrentDialog(context!!, torrent) {
            presenter.onDeleteClick(torrent)
        }
    }

    private val allTorrentsAdapter = AllTorrentsAdapter(onDeleteClick)

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAllTorrentsComponent
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
    ): View = inflater.inflate(R.layout.fragment_all, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onSetup(arguments)
        super.onViewCreated(view, savedInstanceState)

        presenter.onRefresh()

        rv_all_torrents.apply {
            adapter = allTorrentsAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val fab = (activity as MainActivity).findViewById<FloatingActionButton>(R.id.fab)

                    if (dy > 0) {
                        fab.hide()
                    } else {
                        fab.show()
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }

        srl_all_torrents.apply {
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

        srl_all_torrents.isRefreshing = false

        if (torrentFiles.isEmpty()) {
            tv_no_torrents.visibility = View.VISIBLE
            rv_all_torrents.visibility = View.GONE
        } else {
            tv_no_torrents.visibility = View.GONE
            rv_all_torrents.visibility = View.VISIBLE
            allTorrentsAdapter.torrentFiles = torrentFiles
        }

        allTorrentsAdapter.notifyDataSetChanged()
    }

    override fun setLoading(isLoading: Boolean) {
        srl_all_torrents.isRefreshing = isLoading
    }
}