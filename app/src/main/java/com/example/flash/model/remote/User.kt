package com.example.flash.model.remote

data class User(
    var userID: String?,
    var fullName: String?,
    var mobileNo: String?,
    val emailId: String,
    val password: String
)
