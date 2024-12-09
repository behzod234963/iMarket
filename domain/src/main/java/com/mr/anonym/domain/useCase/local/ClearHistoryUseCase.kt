package com.mr.anonym.domain.useCase.local

import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.domain.repository.local.HistoryRepository

class ClearHistoryUseCase(private val repository: HistoryRepository) {

    suspend fun execute(history: List<SearchHistoryModel>){
        repository.clearHistory(history)
    }
}