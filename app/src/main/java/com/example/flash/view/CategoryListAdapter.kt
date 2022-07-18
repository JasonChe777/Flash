package com.example.flash.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flash.SubCategoryActivity
import com.example.flash.databinding.ViewCategoryItemBinding
import com.example.flash.model.remote.data.category.Category
import com.example.flash.model.remote.Constants.BASE_IMAGE_URL

class CategoryListAdapter(private val categoryList: List<Category>) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {
    private lateinit var binding: ViewCategoryItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ViewCategoryItemBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.apply {
            val category = categoryList[position]
            title.text = category.category_name
            Glide.with(image.context)
                .load(BASE_IMAGE_URL+category.category_image_url)
                .into(image)

            item.setOnClickListener {
                val intent = Intent(item.context,SubCategoryActivity::class.java)
                intent.putExtra(SUBCATEGORY_ID,category.category_id)
                item.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val image = binding.categoryImage
        val title = binding.categoryTitle
        val item = binding.categoryItem
    }

    companion object{
        const val SUBCATEGORY_ID = "subcategory_id"
    }

}