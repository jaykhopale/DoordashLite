package com.jayantkhopale.doordash.lite.api

import com.jayantkhopale.doordash.lite.inject.IoDispatcher
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import javax.inject.Inject

/**
 * A repository to fetch a list of stores for a given location. This repository also fetches store
 * details for a given id.
 */
@ViewModelScoped
class StoresRepository @Inject constructor(
    private val storesService: StoresService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    fun getStores(retries: Int = 3) = flow {
        emit(storesService.getStoresByLatLong())
    }.flowOn(ioDispatcher)
        .retryWhen { cause, attempt ->
            cause is Exception && attempt < retries
        }

    fun getStoreDetail(id: Int, retries: Int = 3) = flow {
        emit(storesService.getStoreDetails(id))
    }.flowOn(ioDispatcher)
        .retryWhen { cause, attempt ->
            cause is Exception && attempt < retries
        }
}