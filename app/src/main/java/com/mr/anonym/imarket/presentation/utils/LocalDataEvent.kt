package com.mr.anonym.imarket.presentation.utils

import com.mr.anonym.domain.model.SearchHistoryModel

sealed class LocalDataEvent {
    data class InsertSearchHistory(val historyModel: SearchHistoryModel) : LocalDataEvent()
    data class ClearHistory(val history:List<SearchHistoryModel>):LocalDataEvent()
    data object GetAllSearchedHistory : LocalDataEvent()
}