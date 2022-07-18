package com.example.flash.presenter.category

import com.example.flash.model.remote.data.category.CategoryResponse

interface CategoryMVP {
    interface CategoryPresenter{
        fun getCategory()
    }

    interface CategoryView{
        fun setResult(categoryResponse: CategoryResponse)
        fun onLoad(onLoad: Boolean)
        fun setErrorMessage(message:String?)
    }
}