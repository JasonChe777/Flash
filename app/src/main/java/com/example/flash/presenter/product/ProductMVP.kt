package com.example.flash.presenter.product


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