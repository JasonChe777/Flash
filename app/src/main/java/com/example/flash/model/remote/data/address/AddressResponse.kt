package com.example.flash.model.remote.data.address

data class AddressResponse(
    val addresses: ArrayList<Address>,
    val message: String,
    val status: Int
)
