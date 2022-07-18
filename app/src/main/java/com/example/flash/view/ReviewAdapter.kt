package com.example.flash.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flash.databinding.ItemUserReviewBinding
import com.example.flash.model.remote.data.product.Review

class ReviewAdapter(private val reviewList: List<Review>): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    private lateinit var binding: ItemUserReviewBinding

    inner class ReviewViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(review: Review) {
            binding.apply {
                tvUserName.text = review.review_title
                textReviewBody.text = review.review
                tvTitle.text = review.review_title
                ratingBarReview.rating = review.rating.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemUserReviewBinding.inflate(layoutInflater, parent, false)
        return ReviewViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.apply {
            val review = reviewList[position]
            bind(review)
        }
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}