package com.mr.anonym.domain.useCase.local

data class LocalDataUseCases(
    val insertHistoryUseCase: InsertHistoryUseCase,
    val getAllSearchedHistoryUseCase: GetAllSearchedHistoryUseCase,
    val clearHistoryUseCase: ClearHistoryUseCase
)