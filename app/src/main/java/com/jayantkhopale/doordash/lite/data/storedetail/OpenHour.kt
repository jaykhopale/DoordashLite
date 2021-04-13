package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class OpenHour(
    @Json(name = "hour")
    val hour: Int,
    @Json(name = "minute")
    val minute: Int
) : Parcelable