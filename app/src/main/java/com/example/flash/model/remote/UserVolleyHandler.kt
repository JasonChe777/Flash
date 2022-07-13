package com.example.flash.model.remote

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.flash.model.remote.Constants.BASE_URL
import com.example.flash.model.remote.Constants.LOGIN_END_POINT
import com.example.flash.model.remote.Constants.REGISTRATION_END_POINT
import org.json.JSONObject

class UserVolleyHandler(private val context: Context) {

    private var requestQueue: RequestQueue = Volley.newRequestQueue(context)

    fun registerUser(user: User, callback: OperationalCallBack): String {
        val url = BASE_URL + REGISTRATION_END_POINT
        val data = JSONObject()
        var message: String? = null

        data.put("full_name", user.fullName)
        data.put("mobile_no", user.mobileNo)
        data.put("email_id", user.emailId)
        data.put("password", user.password)

        val request = JsonObjectRequest(
            Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                Log.i("tag", message.toString())
                val status = response.getInt("status")
                Log.e("tag", "message is $message")
                if (status == 0) {
                    callback.onSuccess(message.toString())
                } else {
                    callback.onFailure(message.toString())
                }

            }, { error: VolleyError ->
                error.printStackTrace()
                Log.i("tag", "${error.printStackTrace()}")
                callback.onFailure(message.toString())
            })
        requestQueue.add(request)
        return message.toString()
    }

    fun loginUser(user: User, callback: OperationalCallBack): String {
        val url = BASE_URL + LOGIN_END_POINT
        val data = JSONObject()
        var message: String? = null

        data.put("email_id", user.emailId)
        data.put("password", user.password)

        val request = JsonObjectRequest(Request.Method.POST, url, data,
            { response: JSONObject ->
                message = response.getString("message")
                Log.i("tag", message.toString())
                val status = response.getInt("status")
                Log.e("tag", "message is $message")
                if (status == 0) {
                    val responseUser = response.getJSONObject("user")
                    user.userID = responseUser.getString("user_id")
                    user.fullName = responseUser.getString("full_name")
                    user.mobileNo = responseUser.getString("mobile_no")
                    callback.onSuccess(message.toString())
                } else {
                    callback.onFailure(message.toString())
                }
            }, { error: VolleyError ->
                error.printStackTrace()
                Log.i("tag", "${error.printStackTrace()}")
                callback.onFailure(message.toString())
            })
        requestQueue.add(request)
        return message.toString()
    }
}