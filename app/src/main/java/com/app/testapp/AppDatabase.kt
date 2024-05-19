package com.app.testapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}