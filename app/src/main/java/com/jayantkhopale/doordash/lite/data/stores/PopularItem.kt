package com.jayantkhopale.doordash.lite.data.stores


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularItem(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "img_url")
    val imgUrl: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Int
)