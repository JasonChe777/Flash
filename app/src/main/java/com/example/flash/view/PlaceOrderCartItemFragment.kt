package com.example.flash.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flash.R
import com.example.flash.model.local.CartDao
import com.example.flash.model.local.CartItem

class PlaceOrderCartItemFragment : Fragment() {
    lateinit var adapter: PlaceOrderCartItemAdapter
    lateinit var cartItemList: ArrayList<CartItem>
    lateinit var cartView: View
    private lateinit var cartDao: CartDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_place_order_cart_item, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    @SuppressLint("CutPasteId", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartView = view
        cartDao = CartDao(view.context)
        cartItemList = cartDao.getAllCartItem()
        var totalAmount = 0.0
        for (i in 0 until cartItemList.size) {
            val cartItem = cartItemList[i]
            totalAmount += cartItem.price * cartItem.count
        }
        view.findViewById<TextView>(R.id.tv_checkout_cart_total_amount_num).text =
            "$ $totalAmount"
        adapter = PlaceOrderCartItemAdapter(view.context, cartItemList)
        cartView.findViewById<RecyclerView>(R.id.rv_checkout_cart_item).layoutManager =
            LinearLayoutManager(view.context)
        cartView.findViewById<RecyclerView>(R.id.rv_checkout_cart_item).adapter = adapter

        val btnCheckoutCartItemNext: Button = view.findViewById(R.id.btn_checkout_next)
        btnCheckoutCartItemNext.setOnClickListener {
            (this.parentFragment as PlaceOrderFragment).nextPager()
        }

    }
}