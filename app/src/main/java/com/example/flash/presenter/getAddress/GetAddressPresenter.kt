package com.example.flash.presenter.getAddress

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.address.AddressResponse
import com.example.flash.model.remote.volleyhandler.AddressVolleyHandler

class GetAddressPresenter(
    private val addressVolleyHandler: AddressVolleyHandler,
    private val view: GetAddressMVP.GetAddressView
) : GetAddressMVP.GetAddressPresenter {

    override fun getAddress(userId: String){
        view.onLoad(true)
        addressVolleyHandler.getAddressFromApi(userId,
            object : OperationalCallBack {
                override fun onSuccess(data: Any) {
                    view.onLoad(false)
                    view.setResult(data as AddressResponse)
                }

                override fun onFailure(message: String?) {
                    view.onLoad(false)
                }
            })
    }
}