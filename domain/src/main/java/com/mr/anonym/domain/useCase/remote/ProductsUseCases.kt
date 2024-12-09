package com.mr.anonym.domain.useCase.remote

data class ProductsUseCases(
    val getAllUseCase:GetAllUseCase,
    val searchProductsUseCase:SearchProductsUseCase,
    val getAllCategory: GetAllCategory,
    val getProductsByCategory: GetProductsByCategory
)