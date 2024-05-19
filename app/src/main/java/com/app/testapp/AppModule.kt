package com.app.testapp

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): RetrofitApi = Retrofit.Builder().baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApi::class.java)

    @Provides
    fun provideSampleDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext, AppDatabase::class.java, "MyDatabase.db"
        ).build()

    @Provides
    @Singleton
    fun provideDataRepositoryImpl(api: RetrofitApi , userDao: UserDao): DataRepositoryImpl {
        return DataRepositoryImpl(api,userDao)
    }





}