package com.example.flash.presenter.category

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.category.CategoryResponse
import com.example.flash.model.remote.volleyhandler.CategoryVolleyHandler

class CategoryPresenter(
    private val categoryVolleyHandler: CategoryVolleyHandler,
    val categoryView: CategoryMVP.CategoryView
) : CategoryMVP.CategoryPresenter {
    override fun getCategory() {
        categoryVolleyHandler.getCategory(object :OperationalCallBack{
            override fun onSuccess(data: Any) {
                categoryView.setResult(data as CategoryResponse)
                categoryView.onLoad(false)
            }

            override fun onFailure(message: String?) {
                categoryView.setErrorMessage(message)
                categoryView.onLoad(false)
            }

        })
    }


}