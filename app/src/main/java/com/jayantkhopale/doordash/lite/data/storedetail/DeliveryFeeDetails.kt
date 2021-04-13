package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DeliveryFeeDetails(
    @Json(name = "discount")
    val discount: Discount,
    @Json(name = "final_fee")
    val finalFee: FinalFee,
    @Json(name = "original_fee")
    val originalFee: OriginalFee,
    @Json(name = "surge_fee")
    val surgeFee: SurgeFee
) : Parcelable