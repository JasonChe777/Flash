package com.example.flash.model.remote.data.searchproduct

import com.example.flash.model.remote.data.subcategory.Product

data class SearchProductResponse(
    val message: String,
    val products: ArrayList<Product>,
    val status: Int
)