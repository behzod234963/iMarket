package com.mr.anonym.imarket.presentation.event

sealed class ProductsEvent (
    private val isLoading:Boolean = false,
    private val categoryName:String = ""
){

    class IsLoading():ProductsEvent()
}