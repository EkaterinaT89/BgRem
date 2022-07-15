package com.bgrem.domain.repository

import com.bgrem.data.dto.Video
import kotlinx.coroutines.flow.Flow
import java.io.File

interface VideoRepository {

    val data: Flow<Video>

    suspend fun createJob()

    suspend fun getJob(video: File, id: Int): Video

    suspend fun getBg()

}