package com.suitmedia.adhytianara.evelist.data.api

import com.suitmedia.adhytianara.evelist.data.Guest
import retrofit2.http.GET

interface GithubUserApi {
    @GET("596dec7f0f000023032b8017")
    suspend fun getGuestList(): List<Guest>
}