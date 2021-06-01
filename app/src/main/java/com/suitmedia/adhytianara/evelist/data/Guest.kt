package com.suitmedia.adhytianara.evelist.data

import com.google.gson.annotations.SerializedName

data class Guest(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("birthdate")
    val birthdate: String,

    val imageUrl: String,
)