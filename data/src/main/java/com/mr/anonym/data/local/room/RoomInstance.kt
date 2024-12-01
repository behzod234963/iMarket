package com.mr.anonym.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.model.UserModel

@Database(entities = [ProductsModel::class,UserModel::class], version = 1)
abstract class RoomInstance: RoomDatabase(){

    abstract val productsDAO: ProductsDAO
    abstract val userDAO: UserDao
}