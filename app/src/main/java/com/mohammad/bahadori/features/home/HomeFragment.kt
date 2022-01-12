package com.mohammad.bahadori.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.mohammad.bahadori.R
import com.mohammad.bahadori.core.asMergedLoadStates
import com.mohammad.bahadori.data.enum.AppConstant
import com.mohammad.bahadori.databinding.FragmentHomeBinding
import com.mohammad.bahadori.domain.enums.CoinOrder
import com.mohammad.bahadori.domain.enums.SortType
import com.mohammad.bahadori.features.base.BaseFragment
import com.mohammad.bahadori.features.home.adapter.CoinAdapter
import com.mohammad.bahadori.features.home.adapter.CoinsLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class HomeFragment : BaseFragment(), AdapterView.OnItemSelectedListener {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var dialog: AlertDialog
    lateinit var adapter: CoinAdapter
    private lateinit var mBinding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        return mBinding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    @ExperimentalPagingApi
    private fun initViews() {
        initRecyclerView()
        initSwipeToRefresh()
        initSortSpinner()
        initFilterDialog()

    }

    private fun initFilterDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        mBinding.ibFilter.setOnClickListener { dialog.show() }
        builder.setTitle("Filter")
        val customLayout = layoutInflater.inflate(R.layout.dialog_filter, null)
        builder.setView(customLayout)
        customLayout.findViewById<View>(R.id.btn_apply_dialogFragment).setOnClickListener {
            Toast.makeText(requireContext(), "Not implemented yet", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        dialog = builder.create()
    }

    private fun initSwipeToRefresh() {
        mBinding.srlFragmentHome.setOnRefreshListener { adapter.refresh() }
    }

    private fun initSortSpinner() {
        ArrayAdapter.createFromResource(requireContext(), R.array.sort_type, R.layout.item_spinner)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                mBinding.spSort.adapter = adapter

            }
        mBinding.spSort.onItemSelectedListener = this
    }

    @ExperimentalPagingApi
    @OptIn(InternalCoroutinesApi::class)
    private fun initRecyclerView() {
        adapter = CoinAdapter()
        mBinding.rvHomeCrypto.adapter = adapter.withLoadStateFooter(
            footer = CoinsLoadStateAdapter(adapter)
        )

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect { loadStates ->
                mBinding.srlFragmentHome.isRefreshing =
                    loadStates.mediator?.refresh is LoadState.Loading
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.coins.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                // Use a state-machine to track LoadStates such that we only transition to
                // NotLoading from a RemoteMediator load if it was also presented to UI.
                .asMergedLoadStates()
                // Only emit when REFRESH changes, as we only want to react on loads replacing the
                // list.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                // Scroll to top is synchronous with UI updates, even if remote load was triggered.
                .collect {
                    mBinding.rvHomeCrypto.scrollToPosition(0)
                }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when (position) {
            0->{
                AppConstant.sortType = SortType.ASC
                AppConstant.coinOrder = CoinOrder.POSITION
            }
            1 -> {
                AppConstant.sortType = SortType.ASC
                AppConstant.coinOrder = CoinOrder.MARKET_CAP
            }
            2 -> {
                AppConstant.sortType = SortType.DESC
                AppConstant.coinOrder = CoinOrder.MARKET_CAP
            }
            3 -> {
                AppConstant.sortType = SortType.ASC
                AppConstant.coinOrder = CoinOrder.NAME
            }
            4 -> {
                AppConstant.sortType = SortType.DESC
                AppConstant.coinOrder = CoinOrder.NAME
            }
            5 -> {
                AppConstant.sortType = SortType.ASC
                AppConstant.coinOrder = CoinOrder.PRICE
            }
            6 -> {
                AppConstant.sortType = SortType.DESC
                AppConstant.coinOrder = CoinOrder.PRICE
            }
            7 -> {
                AppConstant.sortType = SortType.ASC
                AppConstant.coinOrder = CoinOrder.CIRCULATING_SUPPLY
            }
            8 -> {
                AppConstant.sortType = SortType.DESC
                AppConstant.coinOrder = CoinOrder.CIRCULATING_SUPPLY
            }

        }
        viewModel.initFlow()
        adapter.refresh()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}