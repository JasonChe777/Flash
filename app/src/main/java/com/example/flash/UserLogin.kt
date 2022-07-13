package com.example.flash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.flash.databinding.ActivityUserLoginBinding
import com.example.flash.model.remote.User
import com.example.flash.model.remote.UserVolleyHandler
import com.example.flash.presenter.LoginMVP
import com.example.flash.presenter.LoginPresenter

class UserLogin : AppCompatActivity(),LoginMVP.LoginView {

    private lateinit var binding: ActivityUserLoginBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserLoginBinding.inflate(layoutInflater)
        presenter = LoginPresenter(UserVolleyHandler(this), this)
        setContentView(binding.root)
        initEncryptedSharePref()
        initView()
//        checkUserLoginStatus()
    }

    private fun initEncryptedSharePref() {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        encryptedSharedPreferences = EncryptedSharedPreferences.create(
            FILE_NAME,
            mainKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
        editor = encryptedSharedPreferences.edit()
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

//    private fun checkUserLoginStatus() {
//        if (encryptedSharedPreferences.contains(EMAIL)) {
//            intentToHomeScreen()
//        }
//    }
//
//
//    private fun intentToHomeScreen() {
//        startActivity(Intent(this@UserLogin, MainActivity::class.java))
//    }


    override fun setResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoad(isLoading: Boolean) {

    }

    override fun setLogin(user: User) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        encryptedSharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        editor = encryptedSharedPreferences.edit()
        editor.putString(USER_ID, user.userID)
        editor.putString(EMAIL, user.emailId)
        editor.putString(PHONE, user.mobileNo)
        editor.putString(NAME, user.fullName)
        editor.putString(PASSWORD, user.password)
        editor.apply()
        startActivity(intent)
    }

    companion object {
        const val FILE_NAME = "Account Details"
        const val USER_ID = "userID"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val NAME = "name"
        const val PASSWORD = "password"
    }
}