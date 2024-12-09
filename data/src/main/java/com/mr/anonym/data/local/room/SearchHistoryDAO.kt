package com.mr.anonym.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.domain.model.SearchHistoryModel

@Dao
interface SearchHistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(historyModel: SearchHistoryModel)

    @Query("SELECT * FROM historyModel")
    suspend fun getAllSearchedHistory():List<SearchHistoryModel>

    @Delete
    suspend fun clearHistory(history:List<SearchHistoryModel>)
}