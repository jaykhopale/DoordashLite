package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Discount(
    @Json(name = "amount")
    val amount: Amount,
    @Json(name = "description")
    val description: String,
    @Json(name = "discount_type")
    val discountType: String,
    @Json(name = "min_subtotal")
    val minSubtotal: MinSubtotal,
    @Json(name = "source_type")
    val sourceType: String,
    @Json(name = "text")
    val text: String
) : Parcelable