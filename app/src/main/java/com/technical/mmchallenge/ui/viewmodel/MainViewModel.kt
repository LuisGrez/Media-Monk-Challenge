package com.technical.mmchallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.technical.mmchallenge.data.model.Album
import com.technical.mmchallenge.domain.Repo
import com.technical.mmchallenge.vo.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Luis Grez on 05 February 2022
 */


class MainViewModel(private val repo:Repo):ViewModel() {
    val fetchAlbumList = liveData<Resource<List<Album>>>(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getAlbumsList())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}