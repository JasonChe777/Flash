package com.example.flash.model.remote.data.category

import com.example.flash.model.remote.data.category.Category

data class CategoryResponse(
    val categories: List<Category>,
    val message: String,
    val status: Int
)