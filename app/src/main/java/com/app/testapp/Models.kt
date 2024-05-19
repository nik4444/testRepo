package com.app.testapp

import androidx.room.Entity
import androidx.room.PrimaryKey


data class ResultData(
    val `data`: List<UserData>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)

@Entity(tableName = "user")
data class UserData(
    @PrimaryKey(autoGenerate = true) var id: Int = -1,
    var avatar: String? = "",
    var email: String? = "",
    var first_name: String? = "",
    var last_name: String? = ""
)

