package com.example.capstone_project

data class User(
    var uid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val contact: String = "",
    val institute: String = "",
    val email : String = "",
    var isAdmin: Boolean = false
)
