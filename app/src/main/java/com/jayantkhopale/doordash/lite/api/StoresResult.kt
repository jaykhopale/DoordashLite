package com.jayantkhopale.doordash.lite.api

import com.jayantkhopale.doordash.lite.data.stores.Store

/**
 * A sealed class to encapsulate results for a list of stores.
 */
sealed class StoresResult {
    object Loading : StoresResult()
    data class Success(val stores: List<Store>) : StoresResult()
    data class Failure(val exception: Throwable) : StoresResult()
}