package com.mr.anonym.domain.useCase.remote

import com.mr.anonym.domain.model.CategoryModel
import com.mr.anonym.domain.repository.remote.ProductsRepository
import retrofit2.Call

class GetAllCategory(private val repository: ProductsRepository) {

    fun execute():Call<List<CategoryModel>> = repository.getAllCategory()
}