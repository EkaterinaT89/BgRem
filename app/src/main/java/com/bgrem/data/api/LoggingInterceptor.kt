package com.bgrem.data.api

import okhttp3.logging.HttpLoggingInterceptor

fun loggingInterceptor() = HttpLoggingInterceptor()
    .apply {
            level = HttpLoggingInterceptor.Level.BODY
    }