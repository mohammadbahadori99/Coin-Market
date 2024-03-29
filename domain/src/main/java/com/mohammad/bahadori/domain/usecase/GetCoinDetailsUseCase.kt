package com.mohammad.bahadori.domain.usecase


import com.mohammad.bahadori.domain.utils.IoDispatcher
import com.mohammad.bahadori.domain.base.SuspendUseCase
import com.mohammad.bahadori.domain.models.CoinDetailDomainModel
import com.mohammad.bahadori.domain.models.CoinWithAllDetailDomainModel
import com.mohammad.bahadori.domain.repo.CoinRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(private val repo: CoinRepository, @IoDispatcher dispatcher: CoroutineDispatcher):
    SuspendUseCase<Int, CoinWithAllDetailDomainModel>(dispatcher){
    override suspend fun execute(parameters: Int): CoinWithAllDetailDomainModel {
        return repo.getDetailedCoin(parameters)
    }
}