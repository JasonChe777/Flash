package com.example.flash.model.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    var cartId: Long?,
    val productName: String,
    val productId: String,
    val description: String,
    val price: Double,
    var count: Int
): Parcelable
