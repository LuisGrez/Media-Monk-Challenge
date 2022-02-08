package com.technical.mmchallenge.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Luis Grez on 05 February 2022
 */

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int)
}