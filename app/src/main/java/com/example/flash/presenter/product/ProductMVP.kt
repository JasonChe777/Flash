package com.example.flash.presenter.product

import com.example.flash.model.remote.data.product.ProductResponse

interface ProductMVP {
    interface ProductPresenter {
        fun getProduct(productId: String)
    }

    interface ProductView {
        fun setResult(data: Any)
        fun onLoad(onLoad: Boolean)
        fun setErrorMessage(message: String?)
    }
}