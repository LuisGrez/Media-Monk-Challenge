package com.technical.mmchallenge.vo

import com.google.gson.GsonBuilder
import com.technical.mmchallenge.domain.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Luis Grez on 05 February 2022
 */

object RetrofitClient {
    private var BASE_URL = "https://jsonplaceholder.typicode.com/"

    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}
