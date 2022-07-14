package com.example.flash.model.remote.data

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)