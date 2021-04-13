package com.jayantkhopale.doordash.lite.data.stores


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stores(
    @Json(name = "is_first_time_user")
    val isFirstTimeUser: Boolean?,
    @Json(name = "next_offset")
    val nextOffset: Int?,
    @Json(name = "num_results")
    val numResults: Int?,
    @Json(name = "show_list_as_pickup")
    val showListAsPickup: Boolean,
    @Json(name = "sort_order")
    val sortOrder: String,
    @Json(name = "stores")
    val stores: List<Store>
)