package com.technical.mmchallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Luis Grez on 06 February 2022
 */

@Parcelize
data class Photos(
    val albumId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val url: String = "",
    val thumbnailUrl: String = ""
) : Parcelable