package com.example.flash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.flash.databinding.ActivityMainBinding
import com.example.flash.model.remote.data.CategoryResponse
import com.example.flash.model.remote.volleyhandler.CategoryVolleyHandler
import com.example.flash.presenter.category.CategoryMVP
import com.example.flash.presenter.category.CategoryPresenter
import com.example.flash.view.HomeFragment

class MainActivity : AppCompatActivity(),CategoryMVP.CategoryView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var categoryPresenter: CategoryMVP.CategoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryPresenter = CategoryPresenter(CategoryVolleyHandler(this), this)
        categoryPresenter.getCategory()

        supportFragmentManager.beginTransaction().add(R.id.fragment_container,HomeFragment() ).commit()
        initView()
    }


    private fun initView() {
        //setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)

        sharedPreferences = getSharedPreferences(UserLogin.FILE_NAME, MODE_PRIVATE)

        binding.navView.setNavigationItemSelectedListener{ menuItem->
            when(menuItem.itemId){
                R.id.logout->{
                    val intent: Intent = Intent(this@MainActivity, UserLogin::class.java)
                    startActivity(intent)
                }
                R.id.cart -> Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show()
                R.id.order -> Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.home -> Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
            }
            true

        }

        val navigationViewHeader = binding.navView.inflateHeaderView(R.layout.nav_view_header)
        navigationViewHeader.findViewById<TextView>(R.id.nav_header_name).text = sharedPreferences.getString(UserLogin.NAME,"")
        navigationViewHeader.findViewById<TextView>(R.id.nav_header_email).text = sharedPreferences.getString(UserLogin.EMAIL,"")
        navigationViewHeader.findViewById<TextView>(R.id.nav_header_phone).text =sharedPreferences.getString(UserLogin.PHONE,"")

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {

            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun setResult(categoryResponse: CategoryResponse) {
        Toast.makeText(this,categoryResponse.message,Toast.LENGTH_LONG).show()
        Log.d("hi","result ${categoryResponse.message}")
    }

    override fun onLoad(shouldLoad: Boolean) {
        Toast.makeText(this,"load is $shouldLoad",Toast.LENGTH_LONG).show()
    }

    override fun setErrorMessage(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

}