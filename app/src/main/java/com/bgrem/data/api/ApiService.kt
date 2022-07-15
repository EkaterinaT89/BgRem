package com.bgrem.data.api

import com.bgrem.data.dto.Video
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://dev.bgrem.deelvin.com/api"

fun okhttp(vararg interceptors: Interceptor): OkHttpClient = OkHttpClient.Builder()
    .apply {
        interceptors.forEach {
            this.addInterceptor(it)
        }
    }
    .build()

fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface ApiService {
    @GET("/jobs/")
    suspend fun createJob(): Response<Video>

    @Multipart
    @POST("/jobs/{job}/")
    suspend fun getJob(@Part video: MultipartBody.Part,
                       @Path("job") job: Int): Response<Video>

    @GET("/bg/")
    suspend fun getBg(): Response<Video>

}