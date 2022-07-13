package com.example.flash.model.local

data class Cart(
    var cartId: Long?,
    val productName: String,
    val productId: String,
    val description: String,
    val price: Double,
    val categoryId: String,
    val subCategoryId: String,
    val productImageUrl: String,
    var count: Int
)
