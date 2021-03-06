package com.example.flash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.flash.databinding.ActivityMainBinding
import com.example.flash.view.HomeFragment
import com.example.flash.view.SearchProductFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.fragment_container,HomeFragment() ).commit()
        initView()
    }


    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)

        sharedPreferences = getSharedPreferences(UserLogin.FILE_NAME, MODE_PRIVATE)

        binding.navView.setNavigationItemSelectedListener{ menuItem->
            when(menuItem.itemId){
                R.id.logout->{
                    startActivity(Intent(this@MainActivity,UserLogin::class.java))
                    Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_LONG).show()
                }
                R.id.cart -> startActivity(Intent(this@MainActivity,CartActivity::class.java))
                R.id.order -> Toast.makeText(this, "Orders", Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                R.id.home -> binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            true

        }

        val navigationViewHeader = binding.navView.inflateHeaderView(R.layout.nav_view_header)
        navigationViewHeader.findViewById<TextView>(R.id.nav_header_name).text = sharedPreferences.getString(UserLogin.NAME,"")
        navigationViewHeader.findViewById<TextView>(R.id.nav_header_email).text = sharedPreferences.getString(UserLogin.EMAIL,"")
        navigationViewHeader.findViewById<TextView>(R.id.nav_header_phone).text =sharedPreferences.getString(UserLogin.PHONE,"")

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.search){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,SearchProductFragment()).commit()
        }


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }


}