package com.mr.anonym.domain.useCase.local

import com.mr.anonym.domain.model.ProductsEntity
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.repository.local.ProductRepository

class InsertProductUseCase(private val repository: ProductRepository) {

    suspend fun execute(product:ProductsEntity){
        repository.insertProduct(product)
    }
}