package com.mr.anonym.domain.components

sealed class SortType {
    data object Expensive : SortType()
    data object Inexpensive : SortType()
    data object Discount : SortType()
    data object Reviews : SortType()
    data object BestRating : SortType()
}