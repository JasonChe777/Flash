package com.example.flash.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flash.databinding.FragmentSubCategoryBinding
import com.example.flash.model.remote.Constants.SUB_CATEGORY_ID
import com.example.flash.model.remote.data.subcategory.Product
import com.example.flash.model.remote.data.subcategory.SubcategoryProductResponse
import com.example.flash.model.remote.volleyhandler.SubcategoryVolleyHandler
import com.example.flash.presenter.subcategory.SubcategoryMVP
import com.example.flash.presenter.subcategory.SubcategoryPresenter


class SubcategoryFragment : Fragment(), SubcategoryMVP.SubCategoryView {
    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var subCategoryId: String
    private lateinit var presenter: SubcategoryMVP.SubCategoryPresenter
    private lateinit var product: ArrayList<Product>
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubCategoryBinding.inflate(layoutInflater, container, false)
        subCategoryId = arguments?.getString(SUB_CATEGORY_ID).toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SubcategoryPresenter(SubcategoryVolleyHandler(view.context), this)
        presenter.getSubCategoryProducts(subCategoryId)
    }


    override fun setResult(data: Any) {
        product = (data as SubcategoryProductResponse).products
        adapter = ProductAdapter(product)
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.productRecyclerView.adapter = adapter
    }

    override fun onLoad(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}