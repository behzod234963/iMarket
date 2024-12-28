package com.mr.anonym.domain.repository.remote

import com.mr.anonym.domain.model.CategoryModel
import com.mr.anonym.domain.model.ProductsModel
import retrofit2.Call

interface ProductsRepository {

    fun getAllProducts():Call<ProductsModel>

    fun searchProducts(text:String):Call<ProductsModel>

    fun getAllCategory():Call<List<CategoryModel>>

    fun getProductsByCategory(category:String,limit:Int,skip:Int):Call<ProductsModel>
}