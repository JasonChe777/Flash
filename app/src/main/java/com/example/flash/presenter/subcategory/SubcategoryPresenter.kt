package com.example.flash.presenter.subcategory

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.subcategory.SubCategoryResponse
import com.example.flash.model.remote.data.subcategory.SubcategoryProductResponse
import com.example.flash.model.remote.volleyhandler.SubcategoryVolleyHandler

class SubcategoryPresenter(
    private val volleyHandler: SubcategoryVolleyHandler,
    val subCategoryView: SubcategoryMVP.SubCategoryView
) : SubcategoryMVP.SubCategoryPresenter {

    override fun getSubCategory(categoryId: String){
        subCategoryView.onLoad(true)
        volleyHandler.callSubcategoryAPI(categoryId, object: OperationalCallBack{
            override fun onSuccess(data: Any) {
                subCategoryView.setResult(data as SubCategoryResponse)
                subCategoryView.onLoad(false)
            }

            override fun onFailure(message: String?) {
                subCategoryView.onLoad(false)
            }
        })


    }

    override fun getSubCategoryProducts(subCategoryId: String) {
        subCategoryView.onLoad(true)
        volleyHandler.callSubCategoryProductAPI(subCategoryId, object: OperationalCallBack{
            override fun onSuccess(data: Any) {
                subCategoryView.setResult(data as SubcategoryProductResponse)
                subCategoryView.onLoad(false)
            }

            override fun onFailure(message: String?) {
                subCategoryView.onLoad(false)
            }
        })
    }
}