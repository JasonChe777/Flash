package com.example.flash.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flash.MainActivity
import com.example.flash.databinding.FragmentSearchProductBinding
import com.example.flash.model.remote.data.searchproduct.SearchProductResponse
import com.example.flash.model.remote.data.subcategory.Product
import com.example.flash.model.remote.volleyhandler.SearchProductVolleyHandler
import com.example.flash.presenter.searchproduct.SearchProductMVP
import com.example.flash.presenter.searchproduct.SearchProductPresenter

class SearchProductFragment : Fragment(), SearchProductMVP.SearchProductView {

    private lateinit var searchProductPresenter: SearchProductPresenter
    private lateinit var productList: ArrayList<Product>

    private lateinit var adapter: ProductAdapter
    private lateinit var binding: FragmentSearchProductBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentSearchProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchProductPresenter = SearchProductPresenter(SearchProductVolleyHandler(view.context),this)


        binding.btnClose.setOnClickListener {
            startActivity(Intent(view.context, MainActivity::class.java))
        }

        binding.btnSearch.setOnClickListener {
            searchProductPresenter.searchProduct(binding.edtSearchQuery.text.toString())
        }
    }

    override fun setResult(searchProductResponse: SearchProductResponse) {
        productList = searchProductResponse.products
        adapter = ProductAdapter(productList)
        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.adapter = adapter
    }

    override fun onLoad(isLoading: Boolean) {

    }

}