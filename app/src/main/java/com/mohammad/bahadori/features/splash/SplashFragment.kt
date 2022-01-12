package com.mohammad.bahadori.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mohammad.bahadori.features.base.BaseFragment
import com.mohammad.bahadori.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.delay

class SplashFragment : BaseFragment() {

    private lateinit var mBinding: FragmentSplashScreenBinding

    private val delayTime = 3000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            delay(delayTime)
            mNavController.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        }
    }

}