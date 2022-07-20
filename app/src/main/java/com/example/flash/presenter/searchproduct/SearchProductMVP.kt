package com.example.flash.presenter.searchproduct

import com.example.flash.model.remote.data.searchproduct.SearchProductResponse

interface SearchProductMVP {

    interface SearchProductPresenter {
        fun getProduct(subCategoryId: String)
        fun searchProduct(query: String)
    }

    interface SearchProductView {
        fun setResult(searchProductResponse: SearchProductResponse)
        fun onLoad(isLoading: Boolean)
    }
}