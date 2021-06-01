package com.suitmedia.adhytianara.evelist.data

import com.google.gson.annotations.SerializedName

data class Event(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("begin_time")
    val date: String,

    @field:SerializedName("image_path")
    val imageUrl: String,
)