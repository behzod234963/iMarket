package com.mr.anonym.domain.useCase.local

import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.domain.repository.local.HistoryRepository

class InsertHistoryUseCase(private val repository: HistoryRepository) {
    suspend fun execute(historyModel: SearchHistoryModel) {
        repository.insertSearchHistory(historyModel)
    }
}