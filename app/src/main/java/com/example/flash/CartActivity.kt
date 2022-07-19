package com.example.flash

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flash.databinding.ActivityCartBinding
import com.example.flash.model.local.CartDao
import com.example.flash.model.local.CartItem

class CartActivity : AppCompatActivity() {
    lateinit var binding :ActivityCartBinding
    lateinit var cartDao:CartDao
    lateinit var cartItemList:List<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartDao = CartDao(this)
        setUpCartListView()
    }

   private fun setUpCartListView(){
        cartItemList = cartDao.getAllCartItem()
        binding.rvCartScreen.adapter = CartAdapter(cartItemList)

    }
}