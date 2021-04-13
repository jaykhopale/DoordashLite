package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Menu(
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_business_enabled")
    val isBusinessEnabled: Boolean?,
    @Json(name = "is_catering")
    val isCatering: Boolean,
    @Json(name = "menu_version")
    val menuVersion: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "open_hours")
    val openHours: List<List<OpenHour>>,
    @Json(name = "status")
    val status: String,
    @Json(name = "status_type")
    val statusType: String,
    @Json(name = "subtitle")
    val subtitle: String
) : Parcelable