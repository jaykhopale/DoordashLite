package com.jayantkhopale.doordash.lite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.jayantkhopale.doordash.lite.R
import com.jayantkhopale.doordash.lite.databinding.ActivityStoresBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoresActivity : AppCompatActivity() {

    private lateinit var storesBinding: ActivityStoresBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storesBinding = ActivityStoresBinding.inflate(layoutInflater)
        setContentView(storesBinding.root)
        val toolbar = storesBinding.toolbar
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}