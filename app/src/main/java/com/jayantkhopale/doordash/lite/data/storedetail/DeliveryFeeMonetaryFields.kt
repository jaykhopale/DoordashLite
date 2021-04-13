package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DeliveryFeeMonetaryFields(
    @Json(name = "currency")
    val currency: String,
    @Json(name = "decimal_places")
    val decimalPlaces: Int,
    @Json(name = "display_string")
    val displayString: String,
    @Json(name = "unit_amount")
    val unitAmount: Int
) : Parcelable