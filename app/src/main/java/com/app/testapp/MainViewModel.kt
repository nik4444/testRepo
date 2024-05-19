package com.app.testapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.testapp.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: DataRepositoryImpl) :
    ViewModel() {

    private val _data = MutableStateFlow<Resource<ResultData>>(Resource.Loading)
    val data: StateFlow<Resource<ResultData>> = _data

    private val _dataFromDb = MutableStateFlow<List<UserData>>(listOf())
    val dataFromDb: StateFlow<List<UserData>> = _dataFromDb

    init {
        fetchData()
    }

    private fun fetchData() = viewModelScope.launch {
        dataRepository.fetchData().onEach {
            _data.value = it

            if (_data.value is Resource.Error) {
                Log.e("ERROR", (_data.value as Resource.Error).message)
            }
        }.launchIn(viewModelScope)
    }

     fun getDataFromDb() {
        viewModelScope.launch {
            dataRepository.getDataFromDb().onEach {
                _dataFromDb.value = it
            }.launchIn(viewModelScope)
        }

    }
}