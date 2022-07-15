package com.example.flash.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.flash.model.remote.Constants.BASE_URL
import com.example.flash.model.remote.Constants.CATEGORY_ID
import com.example.flash.model.remote.Constants.PRODUCTS_END_POINT
import com.example.flash.model.remote.Constants.SUB_CATEGORY_END_POINT
import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.subcategory.SubCategoryResponse
import com.example.flash.model.remote.data.subcategory.SubcategoryProductResponse
import com.google.gson.Gson

class SubcategoryVolleyHandler(private val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callSubcategoryAPI(value_of_category_id:String, callBack: OperationalCallBack) {
        val url = "$BASE_URL$SUB_CATEGORY_END_POINT$CATEGORY_ID=$value_of_category_id"
        val gson = Gson()
        val request = StringRequest(Request.Method.GET, url,
            {response->
                val subCategoryResponse = gson.fromJson(response.toString(), SubCategoryResponse::class.java)
                callBack.onSuccess(subCategoryResponse)
            }){
            callBack.onFailure(it.message)
        }
        requestQueue.add(request)
    }

    fun callSubCategoryProductAPI(sub_category_id:String, callBack: OperationalCallBack){

        val url = BASE_URL + PRODUCTS_END_POINT + sub_category_id
        val gson = Gson()
        val request = StringRequest(Request.Method.GET, url,
            {response->
                val subcategoryProductResponse = gson.fromJson(response.toString(), SubcategoryProductResponse::class.java)
                callBack.onSuccess(subcategoryProductResponse)
        }){
            callBack.onFailure(it.message)
        }
        requestQueue.add(request)
    }
}