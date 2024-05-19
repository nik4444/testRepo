package com.app.testapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(sample: UserData)

    @Query("SELECT * FROM user")
    fun getAllSamples(): Flow<List<UserData>>
}