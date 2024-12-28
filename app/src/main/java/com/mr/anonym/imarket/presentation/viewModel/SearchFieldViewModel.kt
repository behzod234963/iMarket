package com.mr.anonym.imarket.presentation.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CategoryModel
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.domain.useCase.local.LocalDataUseCases
import com.mr.anonym.domain.useCase.remote.ProductsUseCases
import com.mr.anonym.imarket.presentation.utils.event.LocalDataEvent
import com.mr.anonym.imarket.presentation.utils.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchFieldViewModel @Inject constructor(
    private val productsUseCases: ProductsUseCases,
    private val localDataUseCases: LocalDataUseCases
) : ViewModel() {

    private val _isSearching = mutableStateOf(ProductsState().isLoading)
    val isSearching : State<Boolean> = _isSearching

    @SuppressLint("MutableCollectionMutableState")
    private val _searchingResource = mutableStateOf(ProductsState().products)
    val searchingResource :State<List<ProductsItem>> = _searchingResource

    private val _searchHistory = mutableStateOf( ProductsState().searchHistory)
    val searchHistory:State<List<SearchHistoryModel>> = _searchHistory

    private val _categories = mutableStateOf(ProductsState().categories)
    val categories:State<List<CategoryModel>> = _categories

//    @OptIn(FlowPreview::class)
//    val searchingResource = searchFieldText
//        .debounce(500L)
//        .onEach { _isSearching.update { true } }
//        .combine(_searchingResource) { process, result ->
//
//            if (process.isBlank() || process.isEmpty()) {
//                result
//            } else {
//                delay(1000L)
//                result.filter {
//                    it.matchSearchQuery(process)
//                }
//            }
//        }.onEach {
//            _isSearching.update { true }
//        }.stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000L),
//            _searchingResource.value
//        )

    //    method 1 using StateFlow options

    init {
        onLocalDataEvent(LocalDataEvent.GetAllSearchedHistory)
    }

    private fun getAllProducts() = viewModelScope.launch {

    }

    //    method 2 using API
    fun getSearchedProducts(text: String) = viewModelScope.launch {

        _isSearching.value = true
        delay(1500L)
        productsUseCases.searchProductsUseCase.execute(text).enqueue(object :Callback<ProductsModel>{
            override fun onResponse(p0: Call<ProductsModel>, response: Response<ProductsModel>) {
                if (response.isSuccessful){
                    if (response.body()?.products != null){
                        _searchingResource.value = response.body()?.products!!
                    }
                }else{
                    Log.d("NetworkLogging", "onFailure: !!")
                }
            }
            override fun onFailure(p0: Call<ProductsModel>, p1: Throwable) {
                Log.d("NetworkLogging", "onFailure: ${p1.message}")
            }
        })
        _isSearching.value = false
        Log.d("fix it", "getSearchedProducts: ${_searchingResource.value.size}")
    }

    fun getAllCategory() = viewModelScope.launch {
        productsUseCases.getAllCategory.execute().enqueue(object :Callback<List<CategoryModel>>{
            override fun onResponse(
                p0: Call<List<CategoryModel>>,
                response: Response<List<CategoryModel>>
            ) {
                if (response.isSuccessful && response.body() != null){
                    _categories.value = response.body()!!
                }
            }

            override fun onFailure(p0: Call<List<CategoryModel>>, p1: Throwable) {
                Log.d("NetworkLogging", "onFailureGetAllCategory: ${p1.message}")
            }
        })
    }

    fun onLocalDataEvent(event: LocalDataEvent) = viewModelScope.launch {
        when(event){
            is LocalDataEvent.InsertSearchHistory->{
                localDataUseCases.insertHistoryUseCase.execute(event.historyModel)
            }
            is LocalDataEvent.GetAllSearchedHistory->{
                localDataUseCases.getAllSearchedHistoryUseCase.execute().collect{
                    _searchHistory.value = it
                }
            }
            is LocalDataEvent.ClearHistory -> {
                localDataUseCases.clearHistoryUseCase.execute(event.history)
            }

            is LocalDataEvent.InsertProduct -> TODO()
        }
    }
}