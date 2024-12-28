package com.mr.anonym.imarket.di.module

import com.mr.anonym.data.implementation.local.ProductRepositoryImpl
import com.mr.anonym.data.implementation.local.SearchHistoryRepositoryImpl
import com.mr.anonym.data.implementation.local.UserRepositoryImpl
import com.mr.anonym.data.implementation.remote.ProductsRepositoryImpl
import com.mr.anonym.data.local.room.ProductsDAO
import com.mr.anonym.data.local.room.SearchHistoryDAO
import com.mr.anonym.data.local.room.UserDao
import com.mr.anonym.data.remote.ProductsApi
import com.mr.anonym.domain.repository.local.HistoryRepository
import com.mr.anonym.domain.repository.local.ProductRepository
import com.mr.anonym.domain.repository.local.UserRepository
import com.mr.anonym.domain.repository.remote.ProductsRepository
import com.mr.anonym.domain.useCase.local.ClearHistoryUseCase
import com.mr.anonym.domain.useCase.local.GetAllSearchedHistoryUseCase
import com.mr.anonym.domain.useCase.local.InsertHistoryUseCase
import com.mr.anonym.domain.useCase.local.InsertProductUseCase
import com.mr.anonym.domain.useCase.local.LocalDataUseCases
import com.mr.anonym.domain.useCase.remote.GetAllCategory
import com.mr.anonym.domain.useCase.remote.GetAllUseCase
import com.mr.anonym.domain.useCase.remote.GetProductsByCategory
import com.mr.anonym.domain.useCase.remote.ProductsUseCases
import com.mr.anonym.domain.useCase.remote.SearchProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideProductsUseCases(repository: ProductsRepository) = ProductsUseCases(
        getAllUseCase = GetAllUseCase(repository),
        searchProductsUseCase = SearchProductsUseCase((repository)),
        getAllCategory = GetAllCategory(repository),
        getProductsByCategory = GetProductsByCategory(repository)
    )
    @Provides
    @Singleton
    fun provideLocalDataUseCases(historyRepository: HistoryRepository,productsRepository: ProductRepository) = LocalDataUseCases(
        insertHistoryUseCase = InsertHistoryUseCase(historyRepository),
        getAllSearchedHistoryUseCase = GetAllSearchedHistoryUseCase(historyRepository),
        clearHistoryUseCase = ClearHistoryUseCase(historyRepository),
        insertProductUseCase = InsertProductUseCase(productsRepository)
    )
    @Provides
    @Singleton
    fun provideProductsRepositoryRemote(productsApi: ProductsApi):ProductsRepository = ProductsRepositoryImpl(productsApi)

    @Provides
    @Singleton
    fun provideUserRepositoryLocal(userDao: UserDao):UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideSearchHistoryRepository(historyDAO:SearchHistoryDAO):HistoryRepository = SearchHistoryRepositoryImpl(historyDAO)

    @Provides
    @Singleton
    fun provideProductRepository(productsDao:ProductsDAO):ProductRepository = ProductRepositoryImpl(productsDao)
}