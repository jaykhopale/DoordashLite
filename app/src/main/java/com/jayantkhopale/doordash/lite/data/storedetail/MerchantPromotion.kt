package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MerchantPromotion(
    @Json(name = "delivery_fee")
    val deliveryFee: Int?,
    @Json(name = "delivery_fee_monetary_fields")
    val deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "minimum_subtotal")
    val minimumSubtotal: String?,
    @Json(name = "minimum_subtotal_monetary_fields")
    val minimumSubtotalMonetaryFields: MinimumSubtotalMonetaryFields?,
    @Json(name = "new_store_customers_only")
    val newStoreCustomersOnly: Boolean?
) : Parcelable