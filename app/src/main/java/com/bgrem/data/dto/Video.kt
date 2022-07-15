package com.bgrem.data.dto

import com.google.gson.annotations.SerializedName

data class Video(
    val id: String,
    val duration: Int = 0,
    @SerializedName("is_portrait")
    val isPortrait: Boolean = true,
    @SerializedName("source_url")
    val sourseUrl: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("image_height")
    val imageHeight: Int = 0,
    @SerializedName("image_width")
    val imageWidth: Int = 0,
)