package com.mohammad.bahadori.domain.usecase

import androidx.paging.PagingData
import com.mohammad.bahadori.domain.base.UseCase
import com.mohammad.bahadori.domain.models.CoinDomainModel
import com.mohammad.bahadori.domain.repo.CoinPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsWithPagingUseCase @Inject constructor
    (
    private val coinRepository: CoinPagingRepository,
) :
    UseCase<Int, Flow<PagingData<CoinDomainModel>>>() {
    override fun execute(parameters: Int): Flow<PagingData<CoinDomainModel>> {
        return coinRepository.getCoinByPage(parameters)
    }

}