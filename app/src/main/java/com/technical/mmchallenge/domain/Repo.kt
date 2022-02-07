package com.technical.mmchallenge.domain

import com.technical.mmchallenge.data.model.Album
import com.technical.mmchallenge.data.model.Photos
import com.technical.mmchallenge.vo.Resource

/**
 * Created by Luis Grez on 05 February 2022
 */

interface Repo {
    suspend fun getAlbumsList(): Resource<List<Album>>
    suspend fun getPhotosList(albumId: Int): Resource<List<Photos>>
}