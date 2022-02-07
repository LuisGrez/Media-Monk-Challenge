package com.technical.mmchallenge.data

import com.technical.mmchallenge.data.model.Album
import com.technical.mmchallenge.data.model.Photos
import com.technical.mmchallenge.vo.Resource
import com.technical.mmchallenge.vo.RetrofitClient

/**
 * Created by Luis Grez on 05 February 2022
 */


class DataSource {
    suspend fun getAlbums(): Resource<List<Album>> {
        return Resource.Success(RetrofitClient.webService.getAlbums())
    }

    suspend fun getPhotosList(albumId: Int): Resource<List<Photos>> {
        return Resource.Success(RetrofitClient.webService.getPhotosList(albumId))
    }
}