package com.technical.mmchallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technical.mmchallenge.base.BaseViewHolder
import com.technical.mmchallenge.data.model.Album
import com.technical.mmchallenge.databinding.AlbumItemBinding

/**
 * Created by Luis Grez on 05 February 2022
 */

class MainAdapter(
    private val context: Context, private val albumList: List<Album>,
    private val itemClickListener: OnAlbumClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnAlbumClickListener {
        fun onAlbumClick(album: Album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(albumList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    inner class MainViewHolder(private val binding: AlbumItemBinding) :
        BaseViewHolder<Album>(binding.root) {
        override fun bind(item: Album, position: Int) {
            binding.titleAlbum.text = item.title
            binding.albumContainer.setOnClickListener { itemClickListener.onAlbumClick(item) }
        }
    }
}