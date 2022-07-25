package com.bgrem.presentation.viewmodel

import androidx.lifecycle.*
import com.bgrem.data.dto.Video
import com.bgrem.data.repositoryImpl.VideoRepositoryImpl
import com.bgrem.domain.repository.VideoRepository
import com.bgrem.domain.model.ModelState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class VideoViewModel () : ViewModel() {

    private val repository: VideoRepository = VideoRepositoryImpl()

    var data: LiveData<Video> = repository.data.asLiveData(Dispatchers.Default)

    private val _dataState = MutableLiveData<ModelState>()
    val dataState: LiveData<ModelState>
        get() = _dataState

    fun getJob(video: File, id: Int) {
        viewModelScope.launch {
            try {
                _dataState.value = ModelState(loading = true)
                repository.getJob(video, id)
                _dataState.value = ModelState()
            } catch (e: Exception) {
                _dataState.value = ModelState(error = true)
            }
        }
    }

    fun createJob() {
        viewModelScope.launch {
            try {
                _dataState.value = ModelState(loading = true)
                repository.createJob()
                _dataState.value = ModelState()
            } catch (e: Exception) {
                _dataState.value = ModelState(error = true)
            }
        }
    }

    fun getBg() {
        viewModelScope.launch {
            try {
                _dataState.value = ModelState(loading = true)
                repository.getBg()
                _dataState.value = ModelState()
            } catch (e: Exception) {
                _dataState.value = ModelState(error = true)
            }
        }
    }

}

