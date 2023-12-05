package com.example.fitlife.data.pref

data class UserModel(
    var email: String,
    val token: String,
    val isLogin: Boolean = false
)