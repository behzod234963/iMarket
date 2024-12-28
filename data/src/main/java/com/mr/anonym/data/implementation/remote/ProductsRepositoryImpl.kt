package com.mr.anonym.data.implementation.remote

import com.mr.anonym.data.remote.ProductsApi
import com.mr.anonym.domain.model.CategoryModel
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.repository.remote.ProductsRepository
import retrofit2.Call

class ProductsRepositoryImpl(private val productsApi: ProductsApi) :ProductsRepository{
    override fun getAllProducts(): Call<ProductsModel> =
        productsApi.getAllProducts()

    override fun searchProducts(text: String): Call<ProductsModel> = productsApi.searchProducts(
        text = text)

    override fun getAllCategory(): Call<List<CategoryModel>>  = productsApi.getAllCategory()

    override fun getProductsByCategory(category: String, limit: Int, skip: Int): Call<ProductsModel>{
        return productsApi.getProductsByCategory(category,limit,skip)
    }
}