package com.example.flash.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flash.databinding.FragmentHomeBinding
import com.example.flash.model.remote.data.category.CategoryResponse
import com.example.flash.model.remote.volleyhandler.CategoryVolleyHandler
import com.example.flash.presenter.category.CategoryMVP
import com.example.flash.presenter.category.CategoryPresenter


class HomeFragment : Fragment(), CategoryMVP.CategoryView {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: CategoryMVP.CategoryPresenter
    private lateinit var categoryAdapter: CategoryListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CategoryPresenter(CategoryVolleyHandler(view.context), this)
        presenter.getCategory()
    }


    override fun setResult(categoryResponse: CategoryResponse) {
        categoryAdapter = CategoryListAdapter(categoryResponse.categories)
        binding.rvHomeScreen.adapter = categoryAdapter
    }


    override fun onLoad(shouldLoad: Boolean) {

    }

    override fun setErrorMessage(message: String?) {

    }

}
