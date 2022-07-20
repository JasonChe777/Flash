package com.example.flash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flash.databinding.ActivityProductBinding
import com.example.flash.model.local.CartItem
import com.example.flash.model.local.CartDao
import com.example.flash.model.remote.Constants.PRODUCT_ID
import com.example.flash.model.remote.data.product.Image
import com.example.flash.model.remote.data.product.ProductResponse
import com.example.flash.model.remote.data.product.Review
import com.example.flash.model.remote.data.product.Specification
import com.example.flash.model.remote.volleyhandler.ProductVolleyHandler
import com.example.flash.presenter.product.ProductMVP
import com.example.flash.presenter.product.ProductPresenter
import com.example.flash.view.ProductViewPagerAdapter
import com.example.flash.view.ReviewAdapter

class ProductActivity : AppCompatActivity(), ProductMVP.ProductView {
    private lateinit var binding: ActivityProductBinding
    private lateinit var presenter: ProductPresenter
    private lateinit var cartDao: CartDao
    private lateinit var product: com.example.flash.model.remote.data.product.Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.extras?.get(PRODUCT_ID).toString()
        presenter = ProductPresenter(ProductVolleyHandler(this), this)
        cartDao = CartDao(this)
        getProduct(productId)
    }


    private fun getProduct(productId: String) {
        presenter.getProduct(productId)
    }

    override fun setResult(data: Any) {
        product = (data as ProductResponse).product
        setUpViews(product)

    }

    private fun setUpViews(product: com.example.flash.model.remote.data.product.Product) {
        binding.apply {
            tvProductName.text = product.product_name
            val price = "$ " + product.price
            tvProductPrice.text = price
            tvProductDescription.text = product.description
            ratingBarProduct.rating = product.average_rating.toFloat()

            setUpImageViewPager(product.images as ArrayList<Image>)
            setUpReviewRecyclerView(product.reviews as ArrayList<Review>)
            setUpSpecification(product.specifications as ArrayList<Specification>)

            btnAddToCart.setOnClickListener {
                val cartItem = CartItem(
                    cartId = null,
                    productName = product.product_name,
                    productId = product.product_id,
                    description = product.description,
                    price = product.price.toDouble(),
                    count = numberPicker.value
                )
                cartDao.addCartItem(cartItem)
                setUpDialogBox()
            }

        }
    }

    private fun setUpDialogBox() {
        val builder = AlertDialog.Builder(this)
            .setTitle("Success!")
            .setMessage("Item has been added to cart!")
            .setNeutralButton("Continue Shopping", null)
            .setPositiveButton("View Cart") { _, _ ->
                startActivity(
                    Intent(
                        this@ProductActivity,
                        CartActivity::class.java
                    )
                )
            }
        val alertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun setUpSpecification(specifications: ArrayList<Specification>) {
        for (specification in specifications) {
            val row = TableRow(applicationContext)
            val title = TextView(applicationContext)
            val detail = TextView(applicationContext)

            val layoutParams = TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            layoutParams.setMargins(0, 0, 0, 15)

            title.layoutParams = layoutParams
            detail.layoutParams = layoutParams

            title.text = specification.title
            title.textSize = 15F
            detail.text = specification.specification
            detail.textSize = 15F
            detail.setTextColor(resources.getColor(R.color.backgroundColor))

            row.addView(title)
            row.addView(detail)
            binding.tablelayoutSpecification.addView(row)
        }

    }

    private fun setUpReviewRecyclerView(reviews: ArrayList<Review>) {
        val adapter = ReviewAdapter(reviews)
        binding.rvReview.layoutManager = LinearLayoutManager(this)
        binding.rvReview.adapter = adapter
    }

    private fun setUpImageViewPager(images: ArrayList<Image>) {
        val adapter = ProductViewPagerAdapter(images)
        binding.viewPagerImageProduct.adapter = adapter
        binding.circleIndicator.setViewPager(binding.viewPagerImageProduct)
    }

    override fun onLoad(isLoading: Boolean) {

    }

    override fun setErrorMessage(message: String?) {

    }
}