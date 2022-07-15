package com.example.flash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.flash.databinding.ActivityUserRegistrationBinding
import com.example.flash.model.remote.data.user.User
import com.example.flash.model.remote.volleyhandler.UserVolleyHandler
import com.example.flash.presenter.registration.RegistrationMVP
import com.example.flash.presenter.registration.RegistrationPresenter

class UserRegistration : AppCompatActivity(), RegistrationMVP.RegistrationView {

    private lateinit var binding: ActivityUserRegistrationBinding
    private lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegistrationBinding.inflate(layoutInflater)
        presenter = RegistrationPresenter(UserVolleyHandler(this), this)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.apply {
            btnRegister.setOnClickListener {
                val name = edtUserName.text.toString()
                val mobileNo = edtMobileNo.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val user = User(null, name, mobileNo, email, password)
                presenter.registerUser(user)
            }
        }
    }

        override fun setResult(message: String?) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        override fun onLoad(isLoading: Boolean) {

        }

        override fun setLogin(user: User) {
            startActivity(Intent(this@UserRegistration, UserLogin::class.java))
        }

}