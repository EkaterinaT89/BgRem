package com.bgrem.data.repositoryImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.bgrem.data.api.Api
import com.bgrem.data.dto.Video
import com.bgrem.domain.errors.ApiException
import com.bgrem.domain.errors.NetWorkException
import com.bgrem.domain.errors.UnknownException
import com.bgrem.domain.repository.VideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import java.io.File
import java.io.IOException

class VideoRepositoryImpl () :
    VideoRepository {

    private val _videoData = MutableLiveData<Video>()
    val videoData: MutableLiveData<Video>
        get() = _videoData

    override val data = _videoData.asFlow().flowOn(Dispatchers.Default)

    override suspend fun createJob() {
        try {
            val response = Api.retrofitService.createJob()
            val job =
                response.body() ?: throw ApiException(response.code(), response.message())
            videoData.value = job

        } catch (e: IOException) {
            throw NetWorkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

    override suspend fun getJob(video: File, id: Int): Video {
        try {
            val media = MultipartBody.Part.createFormData(
                "file", video.toString()
            )
            val response = Api.retrofitService.getJob(media, id)
            val job =
                response.body() ?: throw ApiException(response.code(), response.message())
            videoData.value = job
            return job
        } catch (e: IOException) {
            throw NetWorkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

    override suspend fun getBg() {
        try {
            val response = Api.retrofitService.getBg()
            val bg =
                response.body() ?: throw ApiException(response.code(), response.message())
            videoData.value = bg

        } catch (e: IOException) {
            throw NetWorkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

}