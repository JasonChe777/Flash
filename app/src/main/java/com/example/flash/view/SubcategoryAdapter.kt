package com.example.flash.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flash.model.remote.Constants.SUB_CATEGORY_ID
import com.example.flash.model.remote.data.subcategory.Subcategory

class SubcategoryAdapter(
    fragmentActivity: FragmentActivity,
    private val subCategoryList: ArrayList<Subcategory>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun createFragment(position: Int): Fragment {

        return SubcategoryFragment().apply {
            val bundle = Bundle(1)
            bundle.putString(SUB_CATEGORY_ID, subCategoryList[position].subcategory_id)
            this.arguments = bundle
        }
    }

}