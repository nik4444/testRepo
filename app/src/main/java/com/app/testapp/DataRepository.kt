package com.app.testapp

import com.app.testapp.common.Resource
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    suspend fun fetchData(): Flow<Resource<ResultData>>

    suspend fun insertData(userData: UserData): Any
    suspend fun getDataFromDb(): Flow<List<UserData>>


}