package com.example.flash.presenter.searchproduct

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.searchproduct.SearchProductResponse
import com.example.flash.model.remote.volleyhandler.SearchProductVolleyHandler

class SearchProductPresenter(
    private val searchProductVolleyHandler: SearchProductVolleyHandler,
    private val searchProductView: SearchProductMVP.SearchProductView
) : SearchProductMVP.SearchProductPresenter {
    override fun getProduct(subCategoryId: String) {
        searchProductView.onLoad(true)
        searchProductVolleyHandler.getProductFromApi(subCategoryId,
            object : OperationalCallBack {
                override fun onSuccess(data: Any) {
                    searchProductView.setResult(data as SearchProductResponse)
                    searchProductView.onLoad(false)
                }

                override fun onFailure(message: String?) {
                    searchProductView.onLoad(false)
                }
            })
    }

    override fun searchProduct(query: String){
        searchProductView.onLoad(true)
        searchProductVolleyHandler.searchProductFromApi(query,
            object : OperationalCallBack {
                override fun onSuccess(data: Any) {
                    searchProductView.onLoad(false)
                    searchProductView.setResult(data as SearchProductResponse)
                }

                override fun onFailure(message: String?) {
                    searchProductView.onLoad(false)
                }
            })

    }
}