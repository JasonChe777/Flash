package com.example.flash.presenter

import com.example.flash.model.remote.User

interface RegistrationMVP {

    interface RegistrationPresenter {
        fun registerUser(user: User): String
    }

    interface RegistrationView {
        fun setResult(message: String)
        fun onLoad(isLoading: Boolean)
        fun setLogin(user: User)
    }
}