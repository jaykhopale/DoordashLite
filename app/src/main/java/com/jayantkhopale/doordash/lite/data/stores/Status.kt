package com.jayantkhopale.doordash.lite.data.stores


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "asap_available")
    val asapAvailable: Boolean,
    @Json(name = "asap_minutes_range")
    val asapMinutesRange: List<Int> = emptyList(),
    @Json(name = "pickup_available")
    val pickupAvailable: Boolean,
    @Json(name = "scheduled_available")
    val scheduledAvailable: Boolean,
    @Json(name = "unavailable_reason")
    val unavailableReason: Any?
)