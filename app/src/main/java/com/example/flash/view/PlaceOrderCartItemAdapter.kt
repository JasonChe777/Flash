package com.example.flash.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flash.R
import com.example.flash.model.local.CartItem

class PlaceOrderCartItemAdapter(
    private val context: Context,
    private val cartItemList: ArrayList<CartItem>
) :
    RecyclerView.Adapter<PlaceOrderCartItemAdapter.CartItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.view_cart_sample, parent, false)
        return CartItemHolder(view)
    }

    override fun onBindViewHolder(holder: CartItemHolder, position: Int) {
        holder.apply {
            val info = cartItemList[position]
            tvCartSampleName.text = info.productName
            tvCartSampleAmount.text = "$ ${(info.price * info.count)}"
            tvCartSampleQuantity.text = info.count.toString()
            tvCartSamplePrice.text = "$ ${info.price}"


        }
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }


    inner class CartItemHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvCartSampleName: TextView = view.findViewById(R.id.tv_cart_sample_name)
        val tvCartSamplePrice: TextView = view.findViewById(R.id.tv_cart_sample_price)
        val tvCartSampleQuantity: TextView = view.findViewById(R.id.tv_cart_sample_quantity)
        val tvCartSampleAmount: TextView = view.findViewById(R.id.tv_cart_sample_amount)
    }
}