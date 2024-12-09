package com.mr.anonym.imarket.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedState:SavedStateHandle
) :ViewModel() {

    private val _category = mutableStateOf( "" )
    val category:State<String> = _category

    init {
        viewModelScope.launch {
            savedState.get<String>("category").let {category->
                if (category != null) {
                    _category.value = category
                }
            }
        }
    }
}