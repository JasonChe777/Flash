package com.example.flash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.flash.UserLogin.Companion.EMAIL
import com.example.flash.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var encryptedSharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSharePref()
        initView()
//        initViewPager()
//        initTabLayout()
    }

//    private fun logout() {
//        editor.apply {
//            clear()
//            apply()
//            startActivity(Intent(this@MainActivity,UserLogin::class.java))
//        }
//    }

//    private fun logOutConfirmationDialog() {
//        val builder = AlertDialog.Builder(this)
//            .setTitle("Confirm LogOut")
//            .setMessage("Are you sure to logOut?")
//            .setPositiveButton("Confirm") { _, _ ->
//                makeToast( "User Logout")
//                logout()
//            }
//            .setNegativeButton("Cancel") { _, _ ->
//                makeToast("Logout Cancelled")
//            }
//
//        val alertDialog: AlertDialog = builder.create()
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//    }
//    private fun initTabLayout() {
//        TabLayoutMediator(binding.tabLayout, binding.pager) {
//                tab, pos -> tab.text = (pos+1).toString()
//        }.attach()
//    }

//    private fun initViewPager() {
//        val adapter = ViewPagerAdapter(this, 3)
//        binding.pager.adapter = adapter
//    }


//    private fun setData() {
//        val email = sharedPreferences.getString(EMAIL, "No email found!")
//        binding.textEmail.text = email
//    }


    private fun initSharePref() {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        encryptedSharedPreferences = EncryptedSharedPreferences.create(
            UserLogin.FILE_NAME,
            mainKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        ) as EncryptedSharedPreferences
        editor = encryptedSharedPreferences.edit()
//        setData()
    }

//    private fun makeToast(text: String) {
//        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
//    }

    private fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView

        navigationView.setNavigationItemSelectedListener{ menuItem->
            when(menuItem.itemId){
                R.id.logout->{
                    val intent: Intent = Intent(this@MainActivity, UserLogin::class.java)
                    startActivity(intent)
                }
                else -> {
                    menuItem.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                            || super.onOptionsItemSelected(menuItem)
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            true

        }

        val navView = navigationView.inflateHeaderView(R.layout.nav_view_header);

        val tvNavHeaderName: TextView = navView.findViewById(R.id.nav_header_name)
        val tvNavHeaderEmail: TextView = navView.findViewById(R.id.nav_header_email)
        val tvNavHeaderPhone: TextView = navView.findViewById(R.id.nav_header_phone)

        encryptedSharedPreferences = getSharedPreferences(UserLogin.FILE_NAME, MODE_PRIVATE)
        editor = encryptedSharedPreferences.edit()
        tvNavHeaderName.text =encryptedSharedPreferences.getString(UserLogin.NAME, UserLogin.NAME)
        tvNavHeaderEmail.text =encryptedSharedPreferences.getString(UserLogin.EMAIL, UserLogin.EMAIL)
        tvNavHeaderPhone.text =encryptedSharedPreferences.getString(UserLogin.PHONE, UserLogin.PHONE)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController
        val drawerLayout: DrawerLayout? = findViewById(R.id.drawerLayout)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.cart, R.id.order, R.id.profile),
            drawerLayout
        )
        setupActionBar(navController, appBarConfiguration)


    }

    private fun setupActionBar(navController: NavController, appBarConfiguration: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }

}