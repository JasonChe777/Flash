package com.example.flash.presenter.getAddress

import com.example.flash.model.remote.data.address.AddressResponse

interface GetAddressMVP {
    interface GetAddressPresenter {
        fun getAddress(userId: String)
    }

    interface GetAddressView {
        fun setResult(addressResponse: AddressResponse?)
        fun onLoad(isLoading: Boolean)
    }
}