package com.jayantkhopale.doordash.lite.data.stores


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeliveryFeeMonetaryFields(
    @Json(name = "currency")
    val currency: String,
    @Json(name = "decimal_places")
    val decimalPlaces: Int,
    @Json(name = "display_string")
    val displayString: String,
    @Json(name = "unit_amount")
    val unitAmount: Int?
)