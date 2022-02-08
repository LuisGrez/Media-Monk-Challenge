package com.technical.mmchallenge.domain

import com.technical.mmchallenge.data.model.Album
import com.technical.mmchallenge.data.model.Photos
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Luis Grez on 05 February 2022
 */

interface WebService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("photos")
    suspend fun getPhotosList(
        @Query("albumId") albumId: Int
    ): List<Photos>
}