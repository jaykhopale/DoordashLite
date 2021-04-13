package com.jayantkhopale.doordash.lite.api

import com.jayantkhopale.doordash.lite.data.storedetail.StoreDetail

/**
 * A sealed class to encapsulate results for a store's detail.
 */
sealed class StoreDetailResult {
    object Loading : StoreDetailResult()
    data class Success(val storeDetail: StoreDetail) : StoreDetailResult()
    data class Failure(val exception: Throwable) : StoreDetailResult()
}
