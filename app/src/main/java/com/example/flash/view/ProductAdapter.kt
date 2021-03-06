package com.example.flash.view

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flash.ProductActivity
import com.example.flash.databinding.ItemProductBinding
import com.example.flash.model.remote.Constants.BASE_IMAGE_URL
import com.example.flash.model.remote.Constants.PRODUCT_ID
import com.example.flash.model.remote.data.subcategory.Product

class ProductAdapter(private val productList: ArrayList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var binding: ItemProductBinding

    inner class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            binding.apply {
                Glide.with(view.context)
                    .load(BASE_IMAGE_URL + product.product_image_url)
                    .into(imageProduct)
                tvProductName.text = product.product_name
                tvProductDescription.text = product.description
                tvProductPrice.text = "$ ${product.price}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.apply {
            val product = productList[position]
            bind(product)

            itemView.setOnClickListener {
                val intent = Intent(view.context, ProductActivity::class.java)
                intent.putExtra(PRODUCT_ID, product.product_id)
                view.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}