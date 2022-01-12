package com.mohammad.bahadori.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext


abstract class SuspendUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    suspend operator fun invoke(parameters: P):R {
            return withContext(coroutineDispatcher) {
                execute(parameters).let {
                    it
                }
            }
    }
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
