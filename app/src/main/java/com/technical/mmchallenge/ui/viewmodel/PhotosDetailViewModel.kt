package com.technical.mmchallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.technical.mmchallenge.data.model.Photos
import com.technical.mmchallenge.domain.Repo
import com.technical.mmchallenge.vo.Resource

/**
 * Created by Luis Grez on 06 February 2022
 */

class PhotosDetailViewModel(private val repo: Repo) : ViewModel() {
    fun fetchPhotosList(albumId: Int) = liveData<Resource<List<Photos>>> {
        emit(Resource.Loading())
        try {
            emit(repo.getPhotosList(albumId))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}