package com.mr.anonym.imarket.presentation.utils.state

import com.mr.anonym.domain.model.CategoryModel
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.model.SearchHistoryModel

data class ProductsState(
    val products:List<ProductsItem> = emptyList(),
    val categories:List<CategoryModel> = emptyList(),
    val searchHistory:List<SearchHistoryModel> = emptyList(),
    val productsModel :ProductsItem? = null,
    val isLoading:Boolean = false,
    val isStateChanged:Boolean = false,
)