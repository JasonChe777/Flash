package com.example.flash.presenter.addAddress

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.volleyhandler.AddressVolleyHandler

class AddAddressPresenter(
    private val addressVolleyHandler: AddressVolleyHandler,
    private val view: AddAddressMVP.AddAddressView
) : AddAddressMVP.AddAddressPresenter {

    override fun addAddress(userId: String, title: String, address: String) {
        view.onLoad(true)
         addressVolleyHandler.addAddressToAPI(userId, title, address,
            object : OperationalCallBack {
                override fun onSuccess(data: Any) {
                    view.onLoad(false)
                    view.setResult(data as String)
                }

                override fun onFailure(message: String?) {
                    view.onLoad(false)
                }
            })
    }
}