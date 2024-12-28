package com.mr.anonym.domain.useCase.remote

import android.util.Log
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.repository.remote.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllUseCase(private val repository: ProductsRepository) {

    fun execute(): Call<ProductsModel> = repository.getAllProducts()
}
