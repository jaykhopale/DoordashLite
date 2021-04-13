package com.jayantkhopale.doordash.lite.data.stores


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Menu(
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_catering")
    val isCatering: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "popular_items")
    val popularItems: List<PopularItem>,
    @Json(name = "subtitle")
    val subtitle: String
)