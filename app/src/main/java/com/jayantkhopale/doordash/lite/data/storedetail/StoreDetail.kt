package com.jayantkhopale.doordash.lite.data.storedetail


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class StoreDetail(
    @Json(name = "address")
    val address: Address,
    @Json(name = "asap_time")
    val asapTime: String?,
    @Json(name = "average_rating")
    val averageRating: Double,
    @Json(name = "business")
    val business: Business,
    @Json(name = "business_id")
    val businessId: Int,
    @Json(name = "composite_score")
    val compositeScore: Int,
    @Json(name = "cover_img_url")
    val coverImgUrl: String,
    @Json(name = "delivery_fee")
    val deliveryFee: Int,
    @Json(name = "delivery_fee_details")
    val deliveryFeeDetails: DeliveryFeeDetails,
    @Json(name = "delivery_radius")
    val deliveryRadius: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "extra_sos_delivery_fee")
    val extraSosDeliveryFee: Int,
    @Json(name = "fulfills_own_deliveries")
    val fulfillsOwnDeliveries: Boolean,
    @Json(name = "header_image_url")
    val headerImageUrl: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "inflation_rate")
    val inflationRate: Double,
    @Json(name = "is_consumer_subscription_eligible")
    val isConsumerSubscriptionEligible: Boolean,
    @Json(name = "is_good_for_group_orders")
    val isGoodForGroupOrders: Boolean,
    @Json(name = "is_newly_added")
    val isNewlyAdded: Boolean,
    @Json(name = "is_only_catering")
    val isOnlyCatering: Boolean,
    @Json(name = "max_composite_score")
    val maxCompositeScore: Int,
    @Json(name = "max_order_size")
    val maxOrderSize: Int?,
    @Json(name = "menus")
    val menus: List<Menu>,
    @Json(name = "merchant_promotions")
    val merchantPromotions: List<MerchantPromotion>,
    @Json(name = "name")
    val name: String,
    @Json(name = "number_of_ratings")
    val numberOfRatings: Int,
    @Json(name = "object_type")
    val objectType: String,
    @Json(name = "offers_delivery")
    val offersDelivery: Boolean,
    @Json(name = "offers_pickup")
    val offersPickup: Boolean,
    @Json(name = "phone_number")
    val phoneNumber: String,
    @Json(name = "price_range")
    val priceRange: Int,
    @Json(name = "provides_external_courier_tracking")
    val providesExternalCourierTracking: Boolean,
    @Json(name = "service_rate")
    val serviceRate: Double,
    @Json(name = "should_show_store_logo")
    val shouldShowStoreLogo: Boolean,
    @Json(name = "show_store_menu_header_photo")
    val showStoreMenuHeaderPhoto: Boolean,
    @Json(name = "show_suggested_items")
    val showSuggestedItems: Boolean,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "special_instructions_max_length")
    val specialInstructionsMaxLength: Int?,
    @Json(name = "status")
    val status: String,
    @Json(name = "status_type")
    val statusType: String,
    @Json(name = "tags")
    val tags: List<String>,
    @Json(name = "yelp_biz_id")
    val yelpBizId: String,
    @Json(name = "yelp_rating")
    val yelpRating: Double,
    @Json(name = "yelp_review_count")
    val yelpReviewCount: Int
) : Parcelable