package com.mr.anonym.data.implementation.local

import com.mr.anonym.data.local.room.ProductsDAO
import com.mr.anonym.domain.model.ProductsEntity
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.repository.local.ProductRepository

class ProductRepositoryImpl(private val dao:ProductsDAO):ProductRepository {
    override suspend fun insertProduct(product: ProductsEntity) {
        dao.insertProduct(product)
    }
}