package com.mohammad.bahadori.features.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mohammad.bahadori.R

abstract class BaseFragment : Fragment() {

    protected lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavController()
    }

    private fun initNavController() {
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        mNavController = navHostFragment.navController
    }

}