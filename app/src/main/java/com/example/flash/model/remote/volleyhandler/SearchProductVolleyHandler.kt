package com.example.flash.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.flash.model.remote.Constants.BASE_URL
import com.example.flash.model.remote.Constants.PRODUCTS_END_POINT
import com.example.flash.model.remote.Constants.SEARCH_PRODUCTS_END_POINT
import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.searchproduct.SearchProductResponse
import com.google.gson.Gson

class SearchProductVolleyHandler(private var context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun getProductFromApi(subCategoryId: String, callback: OperationalCallBack){
        val url = BASE_URL + PRODUCTS_END_POINT + subCategoryId
        val gson = Gson()
        val request = StringRequest(
            Request.Method.GET, url,
            {response->
                val searchProductsResponse = gson.fromJson(response.toString(), SearchProductResponse::class.java)
                callback.onSuccess(searchProductsResponse)
            },
            ){
            callback.onFailure(it.message)
        }
        requestQueue.add(request)
    }

    fun searchProductFromApi(query: String, callback: OperationalCallBack) {
        val url = BASE_URL + SEARCH_PRODUCTS_END_POINT + query
        val gson = Gson()
        val request = StringRequest(
            Request.Method.GET, url,
            {response->
                val searchProductsResponse = gson.fromJson(response.toString(), SearchProductResponse::class.java)
                callback.onSuccess(searchProductsResponse)
            },
            ){
            callback.onFailure(it.message)
            }
        requestQueue.add(request)

    }
}