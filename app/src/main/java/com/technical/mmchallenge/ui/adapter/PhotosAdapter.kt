package com.technical.mmchallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.technical.mmchallenge.base.BaseViewHolder
import com.technical.mmchallenge.data.model.Photos
import com.technical.mmchallenge.databinding.PhotodDetailItemBinding

/**
 * Created by Luis Grez on 06 February 2022
 */

class PhotosAdapter(
    private val context: Context, private val photosList: List<Photos>,
    private val itemClickListener: OnPhotoClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPhotoClickListener {
        fun onPhotoClick(photo: Photos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return PhotosViewHolder(PhotodDetailItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PhotosViewHolder -> holder.bind(photosList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    inner class PhotosViewHolder(val binding: PhotodDetailItemBinding) :
        BaseViewHolder<Photos>(binding.root) {
        override fun bind(item: Photos, position: Int) {

            binding.textDetail.text = item.title

            val glideUrl = GlideUrl(
                item.thumbnailUrl,
                LazyHeaders.Builder().addHeader("User-Agent", USER_AGENT).build())

            Glide.with(itemView.context)
                .load(glideUrl)
                .into(binding.photosDetail)

            binding.photosContainer.setOnClickListener { itemClickListener.onPhotoClick(item) }
        }
    }

    companion object {
        const val USER_AGENT =
            "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"
    }
}