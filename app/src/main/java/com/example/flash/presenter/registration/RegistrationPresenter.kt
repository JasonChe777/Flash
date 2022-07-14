package com.example.flash.presenter.registration

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.User
import com.example.flash.model.remote.volleyhandler.UserVolleyHandler

class RegistrationPresenter(
    private val volleyHandler: UserVolleyHandler,
    private val registrationView: RegistrationMVP.RegistrationView
) : RegistrationMVP.RegistrationPresenter {

    override fun registerUser(user: User): String {
        registrationView.onLoad(true)
        val message = volleyHandler.registerUser(user,
            object : OperationalCallBack {
                override fun onSuccess(data: Any) {
                    registrationView.onLoad(false)
                    registrationView.setResult(data as String)
                    registrationView.setLogin(user)
                }

                override fun onFailure(message: String?) {
                    registrationView.onLoad(false)
                    registrationView.setResult(message)
                }
            })
        return message
    }
}