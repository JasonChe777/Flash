package com.example.flash

import android.annotation.SuppressLint
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.flash.databinding.ActivityCartBinding
import com.example.flash.model.local.CartDao
import com.example.flash.model.local.CartItem
import com.example.flash.view.PlaceOrderFragment
import com.example.flash.view.SearchProductFragment

class CartActivity : AppCompatActivity() {
    lateinit var binding :ActivityCartBinding
    lateinit var cartDao:CartDao
    lateinit var cartItemList:ArrayList<CartItem>

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cartDao = CartDao(this)

        setUpCartListView()
        binding.btnPlaceOrder.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.cart_container,PlaceOrderFragment()).commit()
        }
    }


    private fun setUpCartListView(){
        cartItemList = cartDao.getAllCartItem()
        binding.rvCartScreen.adapter = CartAdapter(cartItemList)

    }
}