package com.example.flash.presenter.addAddress

interface AddAddressMVP {

    interface AddAddressPresenter {
        fun addAddress(userId: String, title: String, address: String)
    }

    interface AddAddressView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
    }
}