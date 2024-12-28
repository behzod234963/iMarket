package com.mr.anonym.domain.repository.local

import com.mr.anonym.domain.model.ProductsEntity
import com.mr.anonym.domain.model.ProductsItem

interface ProductRepository {

    suspend fun insertProduct(product:ProductsEntity)
}