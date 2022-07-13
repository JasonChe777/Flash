package com.example.flash

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val totalCount: Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = totalCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Fragment1()
            1 -> Fragment2()
            else -> Fragment3()
        }
    }

}