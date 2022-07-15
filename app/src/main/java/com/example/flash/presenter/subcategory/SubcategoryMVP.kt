package com.example.flash.presenter.subcategory

interface SubcategoryMVP {
    interface SubCategoryPresenter {
        fun getSubCategory(categoryId: String)
        fun getSubCategoryProducts(subCategoryId: String)
    }

    interface SubCategoryView {
        fun setResult(data: Any)
        fun onLoad(isLoading: Boolean)
    }
}