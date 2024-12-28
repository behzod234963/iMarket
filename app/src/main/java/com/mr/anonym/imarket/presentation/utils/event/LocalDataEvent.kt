package com.mr.anonym.imarket.presentation.utils.event

import com.mr.anonym.domain.model.ProductsEntity
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.model.SearchHistoryModel

sealed class LocalDataEvent {
    data class InsertSearchHistory(val historyModel: SearchHistoryModel) : LocalDataEvent()
    data class InsertProduct(val product:ProductsEntity):LocalDataEvent()
    data class ClearHistory(val history:List<SearchHistoryModel>): LocalDataEvent()
    data object GetAllSearchedHistory : LocalDataEvent()
}