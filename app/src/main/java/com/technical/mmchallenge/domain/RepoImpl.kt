package com.technical.mmchallenge.domain

import com.technical.mmchallenge.data.DataSource
import com.technical.mmchallenge.data.model.Album
import com.technical.mmchallenge.data.model.Photos
import com.technical.mmchallenge.vo.Resource

/**
 * Created by Luis Grez on 05 February 2022
 */

class RepoImpl(private val dataSource: DataSource) : Repo {
    override suspend fun getAlbumsList(): Resource<List<Album>> {
        return dataSource.getAlbums()
    }

    override suspend fun getPhotosList(albumId: Int): Resource<List<Photos>> {
        return dataSource.getPhotosList(albumId)
    }
}