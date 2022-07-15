package com.bgrem.presentation.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class BgremApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}