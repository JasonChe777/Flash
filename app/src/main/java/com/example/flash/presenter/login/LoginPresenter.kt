package com.example.flash.presenter.login

import com.example.flash.model.remote.OperationalCallBack
import com.example.flash.model.remote.data.user.User
import com.example.flash.model.remote.volleyhandler.UserVolleyHandler

class LoginPresenter(
    private val volleyHandler: UserVolleyHandler,
    private val loginView: LoginMVP.LoginView
) : LoginMVP.LoginPresenter {

    override fun loginUser(user: User): String {
        loginView.onLoad(true)
        val message = volleyHandler.loginUser(user,
            object : OperationalCallBack {
                override fun onSuccess(data: Any) {
                    loginView.onLoad(false)
                    loginView.setResult(data as String)
                    loginView.setLogin(user)
                }

                override fun onFailure(message: String?) {
                    loginView.onLoad(false)
                    loginView.setResult(message)
                }
            })
        return message
    }
}