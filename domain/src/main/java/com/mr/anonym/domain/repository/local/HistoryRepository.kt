package com.mr.anonym.domain.repository.local

import com.mr.anonym.domain.model.SearchHistoryModel

interface HistoryRepository {
    suspend fun insertSearchHistory(historyModel: SearchHistoryModel)
    suspend fun getAllSearchedHistory():List<SearchHistoryModel>
    suspend fun clearHistory(history:List<SearchHistoryModel>)
}