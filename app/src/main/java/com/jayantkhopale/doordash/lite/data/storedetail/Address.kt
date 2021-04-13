package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "city")
    val city: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double,
    @Json(name = "printable_address")
    val printableAddress: String,
    @Json(name = "shortname")
    val shortname: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "street")
    val street: String,
    @Json(name = "subpremise")
    val subpremise: String,
    @Json(name = "zip_code")
    val zipCode: String
) : Parcelable