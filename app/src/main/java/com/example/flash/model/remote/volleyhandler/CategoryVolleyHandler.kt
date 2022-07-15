package com.example.flash.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.flash.model.remote.Constants.BASE_URL
import com.example.flash.model.remote.Constants.CATEGORY_END_POINT
import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.category.CategoryResponse
import com.google.gson.Gson

class CategoryVolleyHandler (context: Context){
    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun getCategory(callBack: OperationalCallBack){
        val url = BASE_URL+ CATEGORY_END_POINT
        val gson = Gson()
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val categoryResponse: CategoryResponse = gson.fromJson(response.toString(),
                    CategoryResponse::class.java)
                callBack.onSuccess(categoryResponse)

            }){
                callBack.onFailure(it.message)
        }
        requestQueue.add(request)
    }
}