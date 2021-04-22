package com.jayantkhopale.doordash.lite.api

import android.content.Context
import android.content.SharedPreferences
import com.jayantkhopale.doordash.lite.data.stores.Store
import com.jayantkhopale.doordash.lite.inject.IoDispatcher
import com.squareup.moshi.Moshi
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
        val stores = storesService.getStoresByLatLong()
        if (stores.stores.isEmpty()) {
            throw Exception("Successful response cannot be empty")
        }
        emit(stores)
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

    fun saveToLikedStores(store: Store, context: Context) {
        val sharedPreferences = context.getSharedPreferences("FAVORITE_STORES", Context.MODE_PRIVATE)

        val likedStores = sharedPreferences.getStringSet("FAVORITES", setOf())?.toMutableSet()
        if (likedStores?.contains(store.id.toString()) == true) {
            likedStores.remove(store.id.toString())
        } else {
            likedStores?.add(store.id.toString())
        }
        sharedPreferences.edit().putStringSet("FAVORITES", likedStores).apply()
    }

    fun getLikedStores(context: Context): Set<String> {
        val sharedPreferences = context.getSharedPreferences("FAVORITE_STORES", Context.MODE_PRIVATE)
        val likedStores = sharedPreferences.getStringSet("FAVORITES", setOf()) ?: setOf()
        return likedStores
    }

}