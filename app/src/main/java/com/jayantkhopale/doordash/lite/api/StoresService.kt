package com.jayantkhopale.doordash.lite.api

import com.jayantkhopale.doordash.lite.data.storedetail.StoreDetail
import com.jayantkhopale.doordash.lite.data.stores.Stores
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoresService {

    @GET("/v1/store_feed")
    suspend fun getStoresByLatLong(
        @Query("lat") lat: Double = 37.422740,
        @Query("lng") lng: Double = -122.139956,
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int? = 0
    ): Stores


    @GET("/v2/restaurant/{id}")
    suspend fun getStoreDetails(@Path("id") id: Int): StoreDetail
}