package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Business(
    @Json(name = "business_vertical")
    val businessVertical: List<String>?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
) : Parcelable