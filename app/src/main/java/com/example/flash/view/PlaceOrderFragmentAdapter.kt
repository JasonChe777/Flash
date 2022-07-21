package com.example.flash.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PlaceOrderFragmentAdapter(placeOrderFragment: PlaceOrderFragment, private val count: Int):
    FragmentStateAdapter(placeOrderFragment) {
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->PlaceOrderCartItemFragment()
            1->PlaceOrderDeliveryFragment()
            2->PlaceOrderPaymentFragment()
            3->PlaceOrderSummaryFragment()
            else->PlaceOrderCartItemFragment()
        }
    }


}