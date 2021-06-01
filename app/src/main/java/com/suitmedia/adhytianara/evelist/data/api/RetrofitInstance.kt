package com.suitmedia.adhytianara.evelist.data.api

import com.suitmedia.adhytianara.evelist.utils.Constants.Companion.BASE_GUEST_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_GUEST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: GithubUserApi by lazy {
        retrofit.create(GithubUserApi::class.java)
    }
}