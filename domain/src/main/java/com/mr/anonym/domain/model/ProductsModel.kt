package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductsModel(
    @PrimaryKey
    val id:Int
)
