package com.mr.anonym.domain.repository.remote

import com.mr.anonym.domain.model.CategoryModel
import com.mr.anonym.domain.model.ProductsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsRepository {

    fun getAllProducts():Call<ProductsModel>

    fun searchProducts(text:String):Call<ProductsModel>

    fun getAllCategory():Call<List<CategoryModel>>

    fun getProductsByCategory(category:String):Call<ProductsModel>
}