package com.app.testapp

import com.app.testapp.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class DataRepositoryImpl @Inject constructor(
    private val api: RetrofitApi, private val userDao: UserDao
) : DataRepository {

    override suspend fun fetchData() = flow {
        try {
            emit(Resource.Loading)
            val data = api.fetchData()
            data.data.forEach {
                insertData(it)
            }

            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error("Unexpected error occurred!!"))
        } catch (e: IOException) {
            emit(Resource.Error("something went wrong check your internet connection"))
        }
    }

    override suspend fun insertData(userData: UserData) {
        userDao.insertData(userData)
    }

    override suspend fun getDataFromDb(): Flow<List<UserData>> = userDao.getAllSamples()

}