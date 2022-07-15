package com.example.flash.model.remote.data.subcategory

data class SubcategoryProductResponse(

    val message: String,
    val products: ArrayList<Product>,
    val status: Int
)
