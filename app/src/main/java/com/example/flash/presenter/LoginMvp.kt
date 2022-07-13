package com.example.flash.presenter

import com.example.flash.model.remote.User

interface LoginMVP {
    interface LoginPresenter {
        fun loginUser(user: User): String
    }

    interface LoginView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
        fun setLogin(user: User)
    }
}