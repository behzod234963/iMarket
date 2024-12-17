package com.mr.anonym.imarket.presentation.utils.event

sealed class FilterTypeEvent {
    data class IsAvailable(
        val inStockChecked:Boolean = false,
        val outOfStockChecked:Boolean = false,
        val lowStockChecked:Boolean = false
    ):FilterTypeEvent()
    data class RatingContent( val isRatingStatus:Boolean = false ):FilterTypeEvent()
    data class IsHaveReviews(val reviewsStatus:Boolean = false):FilterTypeEvent()
    data class IsPriceEvent(
        val priceFrom:Double = 0.0,
        val priceTo:Double = 0.0
    ):FilterTypeEvent()
    data class BrandsEvent(val brands:ArrayList<String> = ArrayList()):FilterTypeEvent()
    data class FilterUtils(val isFilterApplied:Boolean = false):FilterTypeEvent()
}