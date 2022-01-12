package com.mohammad.bahadori.domain.usecase

import com.mohammad.bahadori.domain.utils.IoDispatcher
import com.mohammad.bahadori.domain.base.SuspendUseCase
import com.mohammad.bahadori.domain.enums.CoinOrder
import com.mohammad.bahadori.domain.enums.SortType
import com.mohammad.bahadori.domain.models.CoinDomainModel
import com.mohammad.bahadori.domain.repo.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repo: CoinRepository, @IoDispatcher dispatcher: CoroutineDispatcher):
    SuspendUseCase<GetCoinsUseCase.Params, List<CoinDomainModel>>(dispatcher) {

    override suspend fun execute(parameters: Params):List<CoinDomainModel> {
        return repo.getAllCoins(parameters.coinOrder,parameters.sortType)
    }

    data class Params(
        val coinOrder: CoinOrder,
        val sortType: SortType
    )
}