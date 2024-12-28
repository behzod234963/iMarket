package com.mr.anonym.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mr.anonym.domain.model.ProductsEntity
import com.mr.anonym.domain.model.ProductsItem
import retrofit2.http.GET

@Dao
interface ProductsDAO {

    @Insert
    suspend fun insertProduct(product:ProductsEntity)

    @Query("SELECT * FROM products")
    fun getAllProductsLocal():LiveData<List<ProductsEntity>>
}