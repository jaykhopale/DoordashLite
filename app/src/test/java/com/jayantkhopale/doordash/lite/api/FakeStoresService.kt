package com.jayantkhopale.doordash.lite.api

import com.jayantkhopale.doordash.lite.data.storedetail.StoreDetail
import com.jayantkhopale.doordash.lite.data.stores.Stores
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.buffer
import okio.source
import retrofit2.mock.BehaviorDelegate

class FakeStoresService(private val delegate: BehaviorDelegate<StoresService>,
                        private val dispatcher: CoroutineDispatcher) : StoresService {

    override suspend fun getStoresByLatLong(lat: Double, lng: Double, limit: Int, offset: Int?): Stores {
        return withContext(dispatcher) {
            val inputStream = javaClass.classLoader!!
                    .getResourceAsStream("api-response/stores_response.json")
            val source = inputStream.source()
            val bufferedSource = source.buffer()
            var jsonResponse = ""
            while (true) {
                val line = bufferedSource.readUtf8Line() ?: break
                jsonResponse += line
            }
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(Stores::class.java)
            delegate.returningResponse(adapter.fromJson(jsonResponse)).getStoresByLatLong()
        }
    }

    override suspend fun getStoreDetails(id: Int): StoreDetail {
        return withContext(dispatcher) {
            val inputStream = javaClass.classLoader!!
                .getResourceAsStream("api-response/store_detail_response.json")
            val source = inputStream.source()
            val bufferedSource = source.buffer()
            var jsonResponse = ""
            while (true) {
                val line = bufferedSource.readUtf8Line() ?: break
                jsonResponse += line
            }
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(StoreDetail::class.java)
            delegate.returningResponse(adapter.fromJson(jsonResponse)).getStoreDetails(id)
        }
    }

    suspend fun getEmptyResponse(lat: Double, lng: Double, limit: Int, offset: Int?): Stores {
        val jsonResponse = "{\n" +
                "  \"num_results\": 0,\n" +
                "  \"is_first_time_user\": true,\n" +
                "  \"sort_order\": \"\",\n" +
                "  \"next_offset\": 0,\n" +
                "  \"show_list_as_pickup\": false,\n" +
                "  \"stores\": []\n" +
                "}"
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Stores::class.java)
        return adapter.fromJson(jsonResponse)!!
    }

    suspend fun getMalformedResponse(lat: Double, lng: Double, limit: Int, offset: Int?): Stores {
        val jsonResponse = "{\n" +
                "  \"num_results\": 0,\n" +
                "  \"is_first_time_user\": true,\n" +
                "  \"sort_order\": \"\",\n" +
                "  \"next_offset\": 0,\n" +
                "  \"show_list_as_pickup\": false,\n" +
                "  \"stores\": []\n"
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Stores::class.java)
        return adapter.fromJson(jsonResponse)!!
    }
}