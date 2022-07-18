package com.example.flash.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flash.databinding.ItemProductImageBinding
import com.example.flash.model.remote.Constants.BASE_IMAGE_URL
import com.example.flash.model.remote.data.product.Image

class ProductViewPagerAdapter(private val imageList: ArrayList<Image>): RecyclerView.Adapter<ProductViewPagerAdapter.ImageViewHolder>() {
    private lateinit var binding: ItemProductImageBinding
    inner class ImageViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind (image: Image) {
            binding.apply {
                Glide.with(view.context)
                    .load(BASE_IMAGE_URL + image.image)
                    .into(imageProduct)
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemProductImageBinding.inflate(layoutInflater, parent, false)
        return ImageViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.apply {
            val image = imageList[position]
            bind(image)
        }
    }


}