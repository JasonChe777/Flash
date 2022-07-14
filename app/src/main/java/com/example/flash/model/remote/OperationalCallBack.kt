package com.example.flash.model.remote

interface OperationalCallBack {
    fun onSuccess(data: Any)
    fun onFailure(message: String?)
}