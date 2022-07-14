package com.example.flash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.flash.databinding.ActivityUserLoginBinding
import com.example.flash.model.remote.data.User
import com.example.flash.model.remote.volleyhandler.UserVolleyHandler
import com.example.flash.presenter.login.LoginMVP
import com.example.flash.presenter.login.LoginPresenter

class UserLogin : AppCompatActivity(), LoginMVP.LoginView {

    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        presenter = LoginPresenter(UserVolleyHandler(this), this)
        setContentView(binding.root)
        initView()

    }

    private fun initView() {

        binding.apply {
            binding.btnLogin.setOnClickListener {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val user = User(null, null, null, email, password)
                presenter.loginUser(user)
            }
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this@UserLogin, UserRegistration::class.java))
        }
    }



    override fun setResult(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoad(isLoading: Boolean) {

    }

    override fun setLogin(user: User) {
        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString(USER_ID, user.userID)
        editor.putString(EMAIL, user.emailId)
        editor.putString(PHONE, user.mobileNo)
        editor.putString(NAME, user.fullName)
        editor.apply()
        startActivity(Intent(this@UserLogin, MainActivity::class.java))
    }

    companion object {
        const val FILE_NAME = "Account Details"
        const val USER_ID = "userID"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val NAME = "name"
    }
}