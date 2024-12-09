package com.mr.anonym.data.remote

import com.mr.anonym.domain.model.CategoryModel
import com.mr.anonym.domain.model.ProductsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsApi {

    @GET("/products?limit=0")
    fun getAllProducts(): Call<ProductsModel>

    @GET("/products/search")
    fun searchProducts(
        @Query("q")text:String
    ):Call<ProductsModel>

    @GET("/products/categories")
    fun getAllCategory():Call<List<CategoryModel>>

    @GET("/products/category/{category}")
    fun getProductsByCategory(@Path("category")category:String):Call<ProductsModel>
}
