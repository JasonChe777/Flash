package com.example.flash.presenter.registration

import com.example.flash.model.remote.data.user.User

interface RegistrationMVP {

    interface RegistrationPresenter {
        fun registerUser(user: User): String
    }

    interface RegistrationView {
        fun setResult(message: String?)
        fun onLoad(isLoading: Boolean)
        fun setLogin(user: User)
    }
}