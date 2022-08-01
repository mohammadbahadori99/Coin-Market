package com.mohammad.bahadori.features.coinDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mohammad.bahadori.features.base.BaseFragment
import com.mohammad.bahadori.databinding.FragmentCoinDetailsOverviewBinding
import com.mohammad.bahadori.domain.models.CoinDetailDomainModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailsOverviewFragment() : BaseFragment() {

    private lateinit var mBinding: FragmentCoinDetailsOverviewBinding
    private val viewModel: CoinDetailsOverviewViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mBinding = FragmentCoinDetailsOverviewBinding.inflate(inflater, container, false)
        mBinding.apply {
            lifecycleOwner=viewLifecycleOwner
            vm=viewModel
        }
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
    }



}