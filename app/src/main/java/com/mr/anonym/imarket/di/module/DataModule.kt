package com.mr.anonym.imarket.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mr.anonym.data.local.dataStore.DataStoreInstance
import com.mr.anonym.data.local.room.ProductsDAO
import com.mr.anonym.data.local.room.RoomInstance
import com.mr.anonym.data.local.room.SearchHistoryDAO
import com.mr.anonym.data.local.room.UserDao
import com.mr.anonym.data.remote.ProductsApi
import com.mr.anonym.imarket.presentation.utils.component.BASE_URL
import com.mr.anonym.imarket.presentation.utils.component.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttp())
            .build()

    @Provides
    @Singleton
    fun provideProductsAPI(retrofit: Retrofit):ProductsApi = retrofit.create(ProductsApi::class.java)

    @Provides
    @Singleton
    fun provideRoomInstance(application:Application): RoomInstance =
        Room.databaseBuilder(
            application,
            RoomInstance::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideProductsDAO(room: RoomInstance): ProductsDAO = room.productsDAO
    @Provides
    @Singleton
    fun provideUserDAO(room: RoomInstance): UserDao = room.userDAO
    @Provides
    @Singleton
    fun provideSearchHistoryDAO(room: RoomInstance):SearchHistoryDAO = room.searchHistoryDAO

    @Provides
    @Singleton
    fun provideDataStoreInstance(@ApplicationContext context: Context):DataStoreInstance = DataStoreInstance(context)
}