package com.mr.anonym.domain.useCase.remote

import com.mr.anonym.domain.components.SortType
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.repository.remote.ProductsRepository
import retrofit2.Call

class GetProductsByCategory(private val repository: ProductsRepository) {

    fun execute(
        category: String,
        limit: Int,
        skip:Int,
        sortType: SortType = SortType.Inexpensive
    ): Call<ProductsModel> = repository.getProductsByCategory(category,limit,skip)
}