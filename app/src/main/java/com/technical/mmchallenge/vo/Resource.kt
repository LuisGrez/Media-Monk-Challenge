package com.technical.mmchallenge.vo

/**
 * Created by Luis Grez on 05 February 2022
 */

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()
}