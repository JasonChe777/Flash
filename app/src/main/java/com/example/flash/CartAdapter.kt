package com.example.flash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flash.databinding.ItemCartProductBinding
import com.example.flash.model.local.CartItem

class CartAdapter(private val cartList: List<CartItem>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private lateinit var binding: ItemCartProductBinding
    inner class CartViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(cartItem: CartItem) {
            binding.apply {
                tvName.text = cartItem.productName
                tvDescription.text = cartItem.description
                val unitPrice = "$ ${cartItem.price}"
                textUnitPrice.text = unitPrice
                btnNumberPicker.value = cartItem.count
                val total = "$ ${cartItem.price.toInt() * cartItem.count}"
                tvPrice.text = total


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCartProductBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.apply {
            bind(cartList[position])
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}