package com.bgrem.presentation.viewmodel

import androidx.lifecycle.*
import com.bgrem.data.dto.Video
import com.bgrem.domain.repository.VideoRepository
import com.bgrem.domain.model.ModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(private val repository: VideoRepository) : ViewModel() {

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

