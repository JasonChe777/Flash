package com.example.flash.model.remote.volleyhandler

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.flash.model.remote.Constants.ADD_ADDRESS_END_POINT
import com.example.flash.model.remote.Constants.BASE_URL
import com.example.flash.model.remote.Constants.GET_ADDRESS_END_POINT
import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.address.AddressResponse
import com.google.gson.Gson
import org.json.JSONObject

class AddressVolleyHandler(private val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun addAddressToAPI(userId: String, title: String, address: String, callback: OperationalCallBack) {
        val url = BASE_URL + ADD_ADDRESS_END_POINT
        val data = JSONObject()
        var message: String? = null

        data.put("user_id", userId)
        data.put("title", title)
        data.put("address", address)

        val request = JsonObjectRequest(
            Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                val status = response.getInt("status")
                if(status == 0){
                    callback.onSuccess(message.toString())
                } else {
                    callback.onFailure(message.toString())
                }

            }, { error: VolleyError ->
                error.printStackTrace()
                callback.onFailure(message.toString())
            })
        requestQueue.add(request)
    }

    fun getAddressFromApi(userId: String, callback: OperationalCallBack){
        val url = BASE_URL + GET_ADDRESS_END_POINT + userId
        val gson = Gson()
        val request = StringRequest(Request.Method.GET, url,
            {response->
                val subCategoryResponse = gson.fromJson(response.toString(), AddressResponse::class.java)
                callback.onSuccess(subCategoryResponse)
            }){
            callback.onFailure(it.message)
        }
        requestQueue.add(request)
    }
}