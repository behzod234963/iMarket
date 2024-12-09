package com.mr.anonym.domain.useCase.remote

import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.repository.remote.ProductsRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchProductsUseCase(private val repository: ProductsRepository) {

    suspend fun execute(text:String): Call<ProductsModel> = repository.searchProducts(text)
}