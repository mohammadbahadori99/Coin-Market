package com.mohammad.bahadori.features

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.mohammad.bahadori.R
import com.mohammad.bahadori.core.hide
import com.mohammad.bahadori.core.show
import com.mohammad.bahadori.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    // it is initialized in onCreate function
    private lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initValues()
        initNavController()
        setupNavigationUiState()
    }

    private fun initValues() {
        preferences = getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE)
    }

    private fun initNavController() {
        // find navHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        // init navController
        navController = navHostFragment.navController
    }


    private fun setupNavigationUiState() {
        // change UI state based on current destination
        // hide or show drawer and bottom navigation configure that in this function
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    mBinding.tlActivityMain.show()
                }
                R.id.splashFragment -> {
                    mBinding.tlActivityMain.hide()

                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean { // setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


    companion object {
        private const val APP_CONFIG = "APP_CONFIG"
        private const val DARK_STATUS = "DARK_STATUS"
    }
}