package com.example.flash.presenter.product

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.product.ProductResponse
import com.example.flash.model.remote.volleyhandler.ProductVolleyHandler

class ProductPresenter(
    private val productVolleyHandler: ProductVolleyHandler,
    private val productView: ProductMVP.ProductView
) : ProductMVP.ProductPresenter {

    override fun getProduct(productId: String) {
        productView.onLoad(true)
        productVolleyHandler.callProductAPI(productId,
            object : OperationalCallBack {
                override fun onSuccess(data: Any) {
                    productView.setResult(data as ProductResponse)
                    productView.onLoad(false)
                }

                override fun onFailure(message: String?) {
                    productView.setErrorMessage(message)
                    productView.onLoad(false)
                }
            })
    }
}