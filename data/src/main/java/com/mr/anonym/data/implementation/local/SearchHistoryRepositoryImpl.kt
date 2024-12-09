package com.mr.anonym.data.implementation.local

import com.mr.anonym.data.local.room.SearchHistoryDAO
import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.domain.repository.local.HistoryRepository

class SearchHistoryRepositoryImpl(private val historyDAO: SearchHistoryDAO):HistoryRepository {
    override suspend fun insertSearchHistory(historyModel: SearchHistoryModel) {
        historyDAO.insertSearchHistory(historyModel)
    }

    override suspend fun getAllSearchedHistory(): List<SearchHistoryModel> = historyDAO.getAllSearchedHistory()

    override suspend fun clearHistory(history: List<SearchHistoryModel>) {
        historyDAO.clearHistory(history)
    }
}