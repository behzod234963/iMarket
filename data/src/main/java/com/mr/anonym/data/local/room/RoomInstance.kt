package com.mr.anonym.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.domain.model.ProductsEntity
import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.domain.model.UserModel

@Database(entities = [ProductsEntity::class,UserModel::class,SearchHistoryModel::class], version = 1)
abstract class RoomInstance: RoomDatabase(){

    abstract val productsDAO: ProductsDAO
    abstract val userDAO: UserDao
    abstract val searchHistoryDAO:SearchHistoryDAO
}