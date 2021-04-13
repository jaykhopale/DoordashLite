package com.jayantkhopale.doordash.lite.data.stores


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MerchantPromotion(
    @Json(name = "category_new_store_customers_only")
    val categoryNewStoreCustomersOnly: Boolean,
    @Json(name = "daypart_constraints")
    val daypartConstraints: List<Any>,
    @Json(name = "delivery_fee")
    val deliveryFee: Any?,
    @Json(name = "delivery_fee_monetary_fields")
    val deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields,
    @Json(name = "id")
    val id: Int,
    @Json(name = "minimum_subtotal")
    val minimumSubtotal: Any?,
    @Json(name = "minimum_subtotal_monetary_fields")
    val minimumSubtotalMonetaryFields: MinimumSubtotalMonetaryFields
)