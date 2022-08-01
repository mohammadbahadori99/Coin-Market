package com.mohammad.bahadori.features.coinDetails

import androidx.lifecycle.*
import com.mohammad.bahadori.domain.models.CoinDetailDomainModel
import com.mohammad.bahadori.domain.models.CoinWithAllDetailDomainModel
import com.mohammad.bahadori.domain.usecase.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailsOverviewViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
) : ViewModel() {

    private var _coin = MutableLiveData<CoinWithAllDetailDomainModel?>()
    val coin: LiveData<CoinWithAllDetailDomainModel?> get() = _coin

    init {
        viewModelScope.launch {
            _coin.value = getCoinDetailsUseCase(savedStateHandle.get<Int>("coinId") ?: -1)
        }
    }
}