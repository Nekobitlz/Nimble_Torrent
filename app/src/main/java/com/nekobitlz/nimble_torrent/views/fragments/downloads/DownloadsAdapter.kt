package com.nekobitlz.nimble_torrent.views.fragments.downloads

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nekobitlz.nimble_torrent.R
import com.nekobitlz.nimble_torrent.repository.database.TorrentData
import kotlinx.android.synthetic.main.item_download.view.*
import kotlin.math.roundToInt

class DownloadsAdapter : RecyclerView.Adapter<DownloadViewHolder>() {

    var torrentFiles: List<TorrentData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder =
        DownloadViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_download, parent, false)
        )

    override fun getItemCount(): Int = torrentFiles.size

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        holder.bind(torrentFiles[position])
    }
}

class DownloadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(torrent: TorrentData) {
        itemView.apply {
            tv_file_name.text = torrent.name
            tv_parent_file_name.text = torrent.filePath
            tv_file_size.text = torrent.size.toString() // TODO() : Convert to Human-Readable

            pb_download.max = 100
            pb_download.progress = torrent.progress.roundToInt()
        }
    }
}
