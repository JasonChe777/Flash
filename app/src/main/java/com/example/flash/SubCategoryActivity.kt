package com.example.flash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.flash.databinding.ActivitySubCategoryBinding
import com.example.flash.model.remote.data.subcategory.SubCategoryResponse
import com.example.flash.model.remote.data.subcategory.Subcategory
import com.example.flash.model.remote.volleyhandler.SubcategoryVolleyHandler
import com.example.flash.presenter.subcategory.SubcategoryMVP
import com.example.flash.presenter.subcategory.SubcategoryPresenter
import com.example.flash.view.CategoryListAdapter.Companion.SUBCATEGORY_ID
import com.example.flash.view.SubcategoryAdapter
import com.google.android.material.tabs.TabLayoutMediator

class SubCategoryActivity : AppCompatActivity(), SubcategoryMVP.SubCategoryView {
    private lateinit var binding: ActivitySubCategoryBinding
    private lateinit var presenter: SubcategoryPresenter
    private lateinit var subCategory: ArrayList<Subcategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryId = intent.extras?.get(SUBCATEGORY_ID) as String
        getSubCategoryList(categoryId)

    }

    private fun getSubCategoryList(categoryId: String) {
        presenter = SubcategoryPresenter(SubcategoryVolleyHandler(this), this)
        presenter.getSubCategory(categoryId)
    }

    private fun setUpViewPager() {
        val adapter = SubcategoryAdapter(this, subCategory)
        binding.viewPagerSubCategory.adapter = adapter
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(binding.tabLayoutSubCategory, binding.viewPagerSubCategory) {
                tab, position ->
            tab.text = subCategory[position].subcategory_name
        }.attach()
    }

    override fun setResult(data: Any) {
        subCategory = (data as SubCategoryResponse).subcategories
        setUpViewPager()
        setUpTabLayout()
    }

    override fun onLoad(isLoading: Boolean) {
        Log.i("tag", isLoading.toString())
    }
}