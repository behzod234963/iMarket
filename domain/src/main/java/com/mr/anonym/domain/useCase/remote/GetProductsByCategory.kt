package com.mr.anonym.domain.useCase.remote

import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.repository.remote.ProductsRepository
import retrofit2.Call

class GetProductsByCategory(private val repository: ProductsRepository) {

    fun execute(category:String): Call<ProductsModel> = repository.getProductsByCategory(category)
}