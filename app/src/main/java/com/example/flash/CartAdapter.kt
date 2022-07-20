package com.example.flash

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flash.databinding.ItemCartProductBinding
import com.example.flash.model.local.CartDao
import com.example.flash.model.local.CartItem

class CartAdapter(private val cartList: ArrayList<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private lateinit var binding: ItemCartProductBinding
    private lateinit var cartDao: CartDao

    inner class CartViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(cartItem: CartItem, position: Int) {
            binding.apply {
                tvName.text = cartItem.productName
                tvDescription.text = cartItem.description
                val unitPrice = "$ ${cartItem.price}"
                tvUnitPrice.text = unitPrice
                tvNumber.text = cartItem.count.toString()
                val total = "$ ${cartItem.price * cartItem.count}"

                tvItemTotalPrice.text = total

                btnIncrease.setOnClickListener {
                    cartDao.increaseCountByOne(cartItem)

                    cartItem.count = cartItem.count + 1
                    tvNumber.text = cartItem.count.toString()

                    tvItemTotalPrice.text = "$ ${cartItem.price * cartItem.count}"

                }
                btnDecrease.setOnClickListener {
                    if (cartItem.count == 1) {
                        cartDao.deleteCartItem(cartItem.productId)

                        cartList.removeAt(position)
                        notifyItemRemoved(position)

                    } else {
                        cartDao.decreaseCountByOne(cartItem)

                        cartItem.count = cartItem.count - 1
                        tvNumber.text = cartItem.count.toString()
                        tvItemTotalPrice.text = "$ ${cartItem.price * cartItem.count}"
                    }

                }
                btnDelete.setOnClickListener {
                    cartDao.deleteCartItem(cartItem.productId)

                    cartList.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemCartProductBinding.inflate(layoutInflater, parent, false)
        cartDao = CartDao(parent.context)
        return CartViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.apply {
            bind(cartList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}