package com.mr.anonym.imarket.presentation.viewModel

import android.annotation.SuppressLint
import android.util.Log
import android.util.Pair
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.components.SortType
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.useCase.remote.ProductsUseCases
import com.mr.anonym.imarket.presentation.utils.event.FilterTypeEvent
import com.mr.anonym.imarket.presentation.utils.state.ProductsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val remoteUseCases: ProductsUseCases
) : ViewModel() {

    private val _category = mutableStateOf("")
    val category: State<String> = _category

    private val _products = mutableStateOf(ProductsState().products)
    val products: State<List<ProductsItem>> = _products

    @SuppressLint("MutableCollectionMutableState")
    private val _filteredProducts = mutableStateOf(ArrayList<ProductsItem>())
    val filteredProducts: State<ArrayList<ProductsItem>> = _filteredProducts
    @SuppressLint("MutableCollectionMutableState")
    private val _brands = mutableStateOf( ArrayList<String>() )
    val brands:State<ArrayList<String>> = _brands

    private val _product = mutableStateOf(ProductsState())
    val product: State<ProductsState> = _product

    private val _isFilterApplied = mutableStateOf( false )
    val isFilterApplied:State<Boolean> = _isFilterApplied

    private val _inStockChecked = mutableStateOf(false)
    val inStockChecked: State<Boolean> = _inStockChecked
    private val _outOfStock = mutableStateOf(false)
    val outOfStock: State<Boolean> = _outOfStock
    private val _lowStock = mutableStateOf(false)
    val lowStock: State<Boolean> = _lowStock
    private val _ratingContentChecked = mutableStateOf(false)
    val ratingContentChecked: State<Boolean> = _ratingContentChecked
    private val _reviewsChecked = mutableStateOf(false)
    val reviewsChecked: State<Boolean> = _reviewsChecked

    private val _priceFrom = mutableDoubleStateOf(0.0)
    val priceFrom: State<Double> = _priceFrom
    private val _priceTo = mutableDoubleStateOf(0.0)
    val priceTo: State<Double> = _priceTo

    private val _priceRange = mutableStateOf(Pair(_priceFrom.doubleValue, _priceTo.doubleValue))

    private val _inStockCount = mutableIntStateOf(0)
    val inStockCount: State<Int> = _inStockCount
    private val _outOfStockCount = mutableIntStateOf(0)
    val outOfStockCount: State<Int> = _outOfStockCount
    private val _lowStockCount = mutableIntStateOf(0)
    val lowStockCount: State<Int> = _lowStockCount
    private val _ratingCount = mutableIntStateOf(0)
    val ratingCount: State<Int> = _ratingCount

    init {
        viewModelScope.launch {
            savedState.get<String>("category").let { category ->
                if (category != null) {
                    _category.value = category
                }
            }
        }
        getProductsByCategory(category.value, SortType.Inexpensive)
    }

    fun getProductsByCategory(category: String, sortType: SortType) = viewModelScope.launch {
        remoteUseCases.getProductsByCategory.execute(category, sortType)
            .enqueue(object : Callback<ProductsModel> {
                override fun onResponse(
                    p0: Call<ProductsModel>,
                    response: Response<ProductsModel>
                ) {
                    if (response.isSuccessful) {
                        _products.value = response.body()?.products!!
                    }
                }

                override fun onFailure(p0: Call<ProductsModel>, throwable: Throwable) {
                    Log.d("NetworkLogging", "onFailureGetByCategory: ${throwable.message}")
                }
            })
    }
    fun onProductsEvent(event: FilterTypeEvent) = viewModelScope.launch {
        when (event) {
            is FilterTypeEvent.IsAvailable -> {
                _inStockChecked.value = event.inStockChecked
                _outOfStock.value = event.outOfStockChecked
                _lowStock.value = event.lowStockChecked
            }
            is FilterTypeEvent.RatingContent -> { _ratingContentChecked.value = event.isRatingStatus }
            is FilterTypeEvent.IsHaveReviews -> { _reviewsChecked.value = event.reviewsStatus }
            is FilterTypeEvent.IsPriceEvent -> {
                _priceFrom.doubleValue = event.priceFrom
                _priceTo.doubleValue = event.priceTo
            }
            is FilterTypeEvent.BrandsEvent -> { _brands.value = event.brands }
            is FilterTypeEvent.FilterUtils -> { _isFilterApplied.value = event.isFilterApplied }
        }
    }
    fun filterByAvailableStatus() = viewModelScope.launch {
        if (_inStockChecked.value) {
            _products.value.forEach {
                if (it.availabilityStatus?.contains("In Stock") == true) {
                    _product.value = ProductsState(productsModel = it)
                }
            }
            if (_filteredProducts.value.isNotEmpty()) {
                _filteredProducts.value.forEach { filteredProduct ->
                    if (product.value.productsModel?.id != null &&
                        product.value.productsModel?.id != filteredProduct.id
                    ) {
                        _filteredProducts.value.add(filteredProduct)
                        _inStockCount.intValue++
                    }
                }
            }
        }
        if (_outOfStock.value) {
            _products.value.forEach {
                if (it.availabilityStatus?.contains("Out of Stock") == true) {
                    _product.value = ProductsState(productsModel = it)
                }
            }
            if (_filteredProducts.value.isNotEmpty()) {
                _filteredProducts.value.forEach { filteredProduct ->
                    if (product.value.productsModel?.id != null &&
                        product.value.productsModel?.id != filteredProduct.id
                    ) {
                        _filteredProducts.value.add(filteredProduct)
                        _outOfStockCount.intValue++
                    }
                }
            }
        }
        if (_lowStock.value) {
            _products.value.forEach {
                if (it.availabilityStatus?.contains("Low stock") == true) {
                    _product.value = ProductsState(productsModel = it)
                }
            }
            if (_filteredProducts.value.isNotEmpty()) {
                _filteredProducts.value.forEach { filteredProduct ->
                    if (product.value.productsModel?.id != null &&
                        product.value.productsModel?.id != filteredProduct.id
                    ) {
                        _filteredProducts.value.add(filteredProduct)
                        _lowStockCount.intValue++
                    }
                }
            }
        }
    }
    fun filterByRating() = viewModelScope.launch {
        try {
            if (_ratingContentChecked.value) {
                _products.value.forEach { product ->
                    if (product.rating!! >= 4.0) {
                        _product.value = ProductsState(productsModel = product)
                    }
                }
                if (_filteredProducts.value.isNotEmpty()) {
                    _filteredProducts.value.forEach { filteredProduct ->
                        if (product.value.productsModel?.id != null &&
                            product.value.productsModel?.id != filteredProduct.id
                        ) {
                            _filteredProducts.value.add(filteredProduct)
                            _ratingCount.intValue++
                        }
                    }
                }
            }
        } catch (exception: NullPointerException) {
            Log.d("NetworkLogging", "filterByRating func : ${exception.message}")
        }
    }
    fun filterByReviews() = viewModelScope.launch {
        if (_reviewsChecked.value) {
            _filteredProducts.value.clear()
            _filteredProducts.value.addAll(_products.value)
        }
    }
    fun filterByPrice() = viewModelScope.launch {
        when (_priceRange.value) {
            _priceRange.value.first.._priceRange.value.second -> {
                try {
                    _products.value.forEach {
                        if (it.price!! in _priceRange.value.first.._priceRange.value.second) {
                            _product.value = ProductsState(productsModel = it)
                        }
                    }
                    if (_filteredProducts.value.isNotEmpty()){
                        _filteredProducts.value.forEach {
                            if (product.value.productsModel?.id != it.id){
                                product.value.productsModel?.let { it1 ->
                                    _filteredProducts.value.add(
                                        it1
                                    )
                                }
                            }
                        }
                    }
                } catch (exception: NullPointerException) {
                    Log.d("NetworkLogging", "filterByPrice: ${exception.message}")
                }
            }
        }
    }
    fun filterByBrand() = viewModelScope.launch {
        if (_brands.value.isNotEmpty()){
            _brands.value.forEach{ brand->
                _products.value.forEach { product->
                    if (brand == product.brand){
                        _filteredProducts.value.add(product)
                    }
                }
            }
        }
    }
    fun onSortEvent(sortType: SortType) = viewModelScope.launch {
        try {
            when (sortType) {
                is SortType.Inexpensive -> {
                    _products.value.sortedBy { it.price }
                }

                SortType.Expensive -> {
                    _products.value.sortedByDescending { it.price }
                }

                SortType.BestRating -> {
                    _products.value.sortedBy { it.rating }
                }

                SortType.Discount -> {
                    _products.value.sortedBy { it.discountPercentage }
                }

                SortType.Reviews -> {
                    _products.value.sortedBy { it.reviews?.size }
                }
            }
        } catch (exception: NullPointerException) {
            Log.d("NetworkLogging", "onSortEvent: ${exception.message}")
        }
    }

    fun resetFilter(){
        _filteredProducts.value.clear()
        _isFilterApplied.value = false
    }
}