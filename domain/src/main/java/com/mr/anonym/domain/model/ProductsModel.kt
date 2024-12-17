package com.mr.anonym.domain.model

import androidx.room.util.query
import java.util.Locale

data class ProductsModel(

    val total: Int? = null,

    val limit: Int? = null,

    val skip: Int? = null,

    val products: List<ProductsItem> = emptyList()
)

data class ProductsItem(

    val images: List<String?>? = null,
    val thumbnail: String? = null,
    val minimumOrderQuantity: Int? = null,
    val rating: Double? = null,
    val returnPolicy: String? = null,
    val description: String? = null,
    val weight: Int? = null,
    val warrantyInformation: String? = null,
    val title: String? = null,
    val tags: List<String?>? = null,
    val discountPercentage: Double? = null,
    val reviews: List<ReviewsItem?>? = null,
    val price: Double? = null,
    val meta: Meta? = null,
    val shippingInformation: String? = null,
    val id: Int? = null,
    val availabilityStatus: String? = null,
    val category: String? = null,
    val stock: Int? = null,
    val sku: String? = null,
    val dimensions: Dimensions? = null,
    val brand: String? = null
) {
    fun matchSearchQuery(text: String): Boolean {

        val matchCombinations = listOf(
            "$title$category".toLowerCase(Locale.ROOT),
            "$title$category".toLowerCase(Locale.ROOT),
            "${title?.first()}${category?.first()}".toLowerCase(Locale.ROOT),
            "${title?.first()}${category?.first()}".toLowerCase(Locale.ROOT)
        )
        return matchCombinations.any{
            it.contains(text, ignoreCase = true)
        }
    }
}

data class Meta(
    val createdAt: String? = null,
    val qrCode: String? = null,
    val barcode: String? = null,
    val updatedAt: String? = null
)

data class Dimensions(
    val depth: Any? = null,
    val width: Any? = null,
    val height: Any? = null
)

data class ReviewsItem(
    val date: String? = null,
    val reviewerName: String? = null,
    val reviewerEmail: String? = null,
    val rating: Int? = null,
    val comment: String? = null
)
