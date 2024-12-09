package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("historyModel")
data class SearchHistoryModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title:String
)
