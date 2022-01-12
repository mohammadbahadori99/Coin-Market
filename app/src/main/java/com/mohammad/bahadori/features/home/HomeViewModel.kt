package com.mohammad.bahadori.features.home

import androidx.lifecycle.*
import androidx.paging.*
import com.mohammad.bahadori.data.enum.AppConstant
import com.mohammad.bahadori.domain.enums.SortType
import com.mohammad.bahadori.domain.models.CoinDomainModel
import com.mohammad.bahadori.domain.usecase.GetCoinsWithPagingUseCase
import com.mohammad.bahadori.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoinsWithPagingUseCase: GetCoinsWithPagingUseCase
) : BaseViewModel() {
    lateinit var coins: Flow<PagingData<CoinDomainModel>>

    init {
        AppConstant.sortType = SortType.ASC
        initFlow()
    }

    fun initFlow() {
        coins = getCoinsWithPagingUseCase(20).cachedIn(viewModelScope)
    }
}
