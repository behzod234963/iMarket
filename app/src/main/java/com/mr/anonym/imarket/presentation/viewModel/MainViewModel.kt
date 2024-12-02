package com.mr.anonym.imarket.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.local.dataStore.DataStoreInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreInstance: DataStoreInstance
):ViewModel(){

    private val _city = mutableStateOf("")
    val city: State<String> = _city

    init {
        getCityFromDataStore()
    }

    private fun getCityFromDataStore() = viewModelScope.launch {
        dataStoreInstance.getCity().collect{
            _city.value = it
        }
    }
}