package com.example.flash.model.remote.data.product

data class ProductResponse(
    val message: String,
    val product: Product,
    val status: Int
)