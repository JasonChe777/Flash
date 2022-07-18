package com.example.flash.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.flash.model.remote.Constants.BASE_URL
import com.example.flash.model.remote.Constants.PRODUCT_DETAIL_END_POINT
import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.product.ProductResponse
import com.google.gson.Gson

class ProductVolleyHandler(private val context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun callProductAPI(product_id: String, callback: OperationalCallBack) {
        val url = BASE_URL + PRODUCT_DETAIL_END_POINT + product_id
        val gson = Gson()
        val request = StringRequest(
            Request.Method.GET, url,
            {response->
                val productResponse = gson.fromJson(response.toString(), ProductResponse::class.java)
                callback.onSuccess(productResponse)
            }
        ) {
            callback.onFailure(it.message)
        }
        requestQueue.add(request)
    }
}