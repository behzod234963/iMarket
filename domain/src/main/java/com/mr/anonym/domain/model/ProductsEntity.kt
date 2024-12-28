package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("products")
data class ProductsEntity(
    @PrimaryKey val id:Int? = null,
    val brand:String = "",
    val isChecked:Boolean = false
)