package com.mr.anonym.domain.useCase.local

import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.domain.repository.local.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllSearchedHistoryUseCase(private val repository: HistoryRepository) {
    suspend fun execute():Flow<List<SearchHistoryModel>> = flow {
        val historyList = repository.getAllSearchedHistory()
        emit(historyList)
    }
}