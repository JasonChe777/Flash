package com.example.flash.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.flash.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class PlaceOrderFragment : Fragment() {
    private lateinit var placeOrderView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_place_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placeOrderView = view

        val adapter = PlaceOrderFragmentAdapter(this, 4)
        view.findViewById<ViewPager2>(R.id.place_order_view_pager).adapter = adapter
        val tabString = arrayOf("Cart", "Delivery", "Payment", "Summary")
        TabLayoutMediator(view.findViewById(R.id.place_order_tabLayout), view.findViewById(R.id.place_order_view_pager)){ tab, position->
            tab.text = tabString[position]

        }.attach()
    }

    fun nextPager(){
        placeOrderView.findViewById<ViewPager2>(R.id.place_order_view_pager).currentItem = placeOrderView.findViewById<ViewPager2>(R.id.place_order_view_pager).currentItem + 1
    }
}