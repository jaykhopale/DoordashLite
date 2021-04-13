package com.jayantkhopale.doordash.lite.data.stores


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.Instant

@JsonClass(generateAdapter = true)
data class Store(
    @Json(name = "average_rating")
    val averageRating: Double,
    @Json(name = "business_id")
    val businessId: Int,
    @Json(name = "cover_img_url")
    val coverImgUrl: String,
    @Json(name = "delivery_fee")
    val deliveryFee: Int,
    @Json(name = "delivery_fee_monetary_fields")
    val deliveryFeeMonetaryFields: DeliveryFeeMonetaryFields,
    @Json(name = "description")
    val description: String,
    @Json(name = "display_delivery_fee")
    val displayDeliveryFee: String,
    @Json(name = "distance_from_consumer")
    val distanceFromConsumer: Double,
    @Json(name = "extra_sos_delivery_fee")
    val extraSosDeliveryFee: Int,
    @Json(name = "extra_sos_delivery_fee_monetary_fields")
    val extraSosDeliveryFeeMonetaryFields: ExtraSosDeliveryFeeMonetaryFields,
    @Json(name = "header_img_url")
    val headerImgUrl: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_consumer_subscription_eligible")
    val isConsumerSubscriptionEligible: Boolean,
    @Json(name = "is_newly_added")
    val isNewlyAdded: Boolean,
    @Json(name = "location")
    val location: Location,
    @Json(name = "menus")
    val menus: List<Menu>,
    @Json(name = "merchant_promotions")
    val merchantPromotions: List<MerchantPromotion>,
    @Json(name = "name")
    val name: String,
    @Json(name = "next_close_time")
    val nextCloseTime: String,
    @Json(name = "next_open_time")
    val nextOpenTime: String,
    @Json(name = "num_ratings")
    val numRatings: Int,
    @Json(name = "price_range")
    val priceRange: Int,
    @Json(name = "promotion_delivery_fee")
    val promotionDeliveryFee: Int,
    @Json(name = "service_rate")
    val serviceRate: Any?,
    @Json(name = "status")
    val status: Status,
    @Json(name = "url")
    val url: String
) {
    val shortDescription =
        description.split(", ").joinToString(separator = ", ", limit = 2, truncated = "...")

    val deliveryTime: String get() {
        return if (Instant.parse(nextOpenTime) > Instant.now()) "Closed" else status
                .asapMinutesRange.first().toString() + " min"
    }

}
