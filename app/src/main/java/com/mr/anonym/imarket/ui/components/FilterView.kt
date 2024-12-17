package com.mr.anonym.imarket.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.Screens
import com.mr.anonym.imarket.presentation.utils.event.FilterTypeEvent
import com.mr.anonym.imarket.presentation.viewModel.CategoryViewModel
import com.mr.anonym.imarket.ui.items.FilterViewBrandItem
import com.mr.anonym.imarket.ui.theme.darkerGray
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterView(
    navController: NavHostController,
    onBackClick: () -> Unit,
    viewModel: CategoryViewModel = hiltViewModel()
) {

    val products = viewModel.products
    val brands = ArrayList<String>()

    val isAvailableClicked = remember { mutableStateOf(false) }
    val isRatingClicked = remember { mutableStateOf(true) }
    val isReviewClicked = remember { mutableStateOf(false) }
    val isPriceClicked = remember { mutableStateOf(false) }
    val isBrandClicked = remember { mutableStateOf(false) }

    val isInStockChecked = remember { mutableStateOf(false) }
    val isOutOfStockChecked = remember { mutableStateOf(false) }
    val isLowStockChecked = remember { mutableStateOf(false) }
    val isRatingChecked = remember { mutableStateOf(false) }
    val isReviewsChecked = remember { mutableStateOf(false) }

    val isLessThanChecked = remember { mutableStateOf(false) }
    val isOneHundredChecked = remember { mutableStateOf(false) }
    val isThreeHundredChecked = remember { mutableStateOf(false) }
    val isFiveHundredChecked = remember { mutableStateOf(false) }
    val isMoreThanChecked = remember { mutableStateOf(false) }

    val isBrandChecked = remember { mutableStateOf( false ) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val priceFrom = remember { mutableStateOf("") }
    val priceTo = remember { mutableStateOf("") }

    val isStateChanged = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(extractMainColor())
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.filter),
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        fontSize = 24.sp,
                    )
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "icon back",
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }
            }
        )
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Black)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.42f)
                    .fillMaxHeight()
                    .background(extractPrimaryMainColor())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                isAvailableClicked.value = true
                                isRatingClicked.value = false
                                isReviewClicked.value = false
                                isBrandClicked.value = false
                                isPriceClicked.value = false
                            }
                            .background(if (isAvailableClicked.value) extractMainColor() else extractPrimaryMainColor())
                            .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.available),
                            color = if (extractMainColor() == darkerGray) Color.White else Color.Black,
                            fontSize = 17.sp,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                isRatingClicked.value = true
                                isAvailableClicked.value = false
                                isReviewClicked.value = false
                                isBrandClicked.value = false
                                isPriceClicked.value = false
                            }
                            .background(if (isRatingClicked.value) extractMainColor() else extractPrimaryMainColor())
                            .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.rating_4_or_more),
                            color = if (extractMainColor() == darkerGray) Color.White else Color.Black,
                            fontSize = 17.sp,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                isReviewClicked.value = true
                                isRatingClicked.value = false
                                isAvailableClicked.value = false
                                isBrandClicked.value = false
                                isPriceClicked.value = false
                            }
                            .background(if (isReviewClicked.value) extractMainColor() else extractPrimaryMainColor())
                            .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.is_have_reviews),
                            color = if (extractMainColor() == darkerGray) Color.White else Color.Black,
                            fontSize = 17.sp,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                isPriceClicked.value = true
                                isRatingClicked.value = false
                                isAvailableClicked.value = false
                                isReviewClicked.value = false
                                isBrandClicked.value = false
                            }
                            .background(if (isPriceClicked.value) extractMainColor() else extractPrimaryMainColor())
                            .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.price),
                            color = if (extractMainColor() == darkerGray) Color.White else Color.Black,
                            fontSize = 17.sp,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                isBrandClicked.value = true
                                isRatingClicked.value = false
                                isAvailableClicked.value = false
                                isReviewClicked.value = false
                                isPriceClicked.value = false
                            }
                            .background(if (isBrandClicked.value) extractMainColor() else extractPrimaryMainColor())
                            .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.brand),
                            color = if (extractMainColor() == darkerGray) Color.White else Color.Black,
                            fontSize = 17.sp,
                        )
                    }
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight(),
                thickness = 1.dp,
                color = Color.Black
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(extractPrimaryMainColor())
                    .padding(10.dp)
            ) {
                if (isAvailableClicked.value) {
                    Column {
                        Text(
                            text = stringResource(R.string.available),
                            color = if (extractPrimaryMainColor() == Color.Black) Color.White else Color.Black,
                            fontSize = 22.sp
                        )
//                           In stock
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isInStockChecked.value
                            ) {
                                isInStockChecked.value = it
                                if (isInStockChecked.value) {
                                    isLowStockChecked.value = false
                                    isOutOfStockChecked.value = false
                                    isStateChanged.value = true
                                } else {
                                    isStateChanged.value = false
                                }
                                viewModel.onProductsEvent(
                                    FilterTypeEvent.IsAvailable(
                                        inStockChecked = isInStockChecked.value,
                                        outOfStockChecked = isOutOfStockChecked.value,
                                        lowStockChecked = isLowStockChecked.value
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.in_stock),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
//                        Out of stock
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isOutOfStockChecked.value
                            ) {
                                isOutOfStockChecked.value = it
                                if (isOutOfStockChecked.value) {
                                    isInStockChecked.value = false
                                    isLowStockChecked.value = false
                                    isStateChanged.value = true
                                } else {
                                    isStateChanged.value = false
                                }
                                viewModel.onProductsEvent(
                                    FilterTypeEvent.IsAvailable(
                                        inStockChecked = isInStockChecked.value,
                                        outOfStockChecked = isOutOfStockChecked.value,
                                        lowStockChecked = isLowStockChecked.value
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.out_of_stock),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
//                        low stock
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isLowStockChecked.value
                            ) {
                                isLowStockChecked.value = it
                                if (isLowStockChecked.value) {
                                    isInStockChecked.value = false
                                    isOutOfStockChecked.value = false
                                    isStateChanged.value = true
                                } else {
                                    isStateChanged.value = false
                                }
                                viewModel.onProductsEvent(
                                    FilterTypeEvent.IsAvailable(
                                        inStockChecked = isInStockChecked.value,
                                        outOfStockChecked = isOutOfStockChecked.value,
                                        lowStockChecked = isLowStockChecked.value
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.exist),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
                    }
                }
                if (isRatingClicked.value) {
                    Column {
                        Text(
                            text = stringResource(R.string.rating),
                            color = if (extractPrimaryMainColor() == Color.Black) Color.White else Color.Black,
                            fontSize = 22.sp
                        )
//                           Rating 4 or more
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isRatingChecked.value
                            ) {
                                isRatingChecked.value = it
                                viewModel.onProductsEvent(
                                    FilterTypeEvent.RatingContent(
                                        isRatingChecked.value
                                    )
                                )
                                isStateChanged.value = isRatingChecked.value
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.rating_4_or_more),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
                if (isReviewClicked.value) {
                    Column {
                        Text(
                            text = stringResource(R.string.is_have_reviews),
                            color = if (extractPrimaryMainColor() == Color.Black) Color.White else Color.Black,
                            fontSize = 22.sp
                        )
//                           reviews
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isReviewsChecked.value
                            ) {
                                isReviewsChecked.value = it
                                viewModel.onProductsEvent(
                                    FilterTypeEvent.IsHaveReviews(
                                        isReviewsChecked.value
                                    )
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.exist),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
                    }
                }
                if (isPriceClicked.value) {
                    Column {
//                         Price
                        Text(
                            text = stringResource(R.string.price),
                            color = if (extractPrimaryMainColor() == Color.Black) Color.White else Color.Black,
                            fontSize = 22.sp
                        )
                        PriceFields(
                            priceFrom = priceFrom.value,
                            onPriceFromValueChange = { priceFrom.value = it },
                            onPriceFromSend = {
                                priceFrom.value.forEach {
                                    if (it.code in 48..57) {
                                        viewModel.onProductsEvent(
                                            FilterTypeEvent.IsPriceEvent(
                                                priceFrom = it.toDouble()
                                            )
                                        )
                                    }else{
                                        viewModel.onProductsEvent(
                                            FilterTypeEvent.IsPriceEvent(
                                                priceFrom = 0.0
                                            )
                                        )
                                    }
                                }
                                keyboardController?.hide()
                            },
                            priceTo = priceTo.value,
                            onPriceToValueChange = { priceTo.value = it },
                            onPriceToSend = {
                                priceTo.value.forEach {
                                    if (it.code in 48..57){
                                        viewModel.onProductsEvent(FilterTypeEvent.IsPriceEvent(priceTo = it.toDouble()))
                                    }else{
                                        viewModel.onProductsEvent(
                                            FilterTypeEvent.IsPriceEvent(
                                                priceFrom = 0.0
                                            )
                                        )
                                    }
                                }
                                keyboardController?.hide()
                            }
                        )
//                        Less than 100$
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isLessThanChecked.value
                            ) {
                                isLessThanChecked.value = it
                                if (isLessThanChecked.value) {
                                    isOneHundredChecked.value = false
                                    isThreeHundredChecked.value = false
                                    isFiveHundredChecked.value = false
                                    isMoreThanChecked.value = false
                                    priceFrom.value = "0"
                                    priceTo.value = "100"
                                } else {
                                    priceFrom.value = ""
                                    priceTo.value = ""
                                }
                                if (priceFrom.value.isDigitsOnly() &&
                                    priceTo.value.isDigitsOnly()
                                ) {
                                    viewModel.onProductsEvent(
                                        FilterTypeEvent.IsPriceEvent(
                                            priceTo = 0.0,
                                            priceFrom = 100.9
                                        )
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.less_than_100),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
//                        101-300$
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isOneHundredChecked.value
                            ) {
                                isOneHundredChecked.value = it
                                if (isOneHundredChecked.value) {
                                    isLessThanChecked.value = false
                                    isThreeHundredChecked.value = false
                                    isFiveHundredChecked.value = false
                                    isMoreThanChecked.value = false
                                    priceFrom.value = "101"
                                    priceTo.value = "300"
                                } else {
                                    priceFrom.value = ""
                                    priceTo.value = ""
                                }
                                if (priceFrom.value.isDigitsOnly() &&
                                    priceTo.value.isDigitsOnly()
                                ) {
                                    viewModel.onProductsEvent(
                                        FilterTypeEvent.IsPriceEvent(
                                            priceTo = 101.0,
                                            priceFrom = 300.9
                                        )
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string._101_300),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
//                        301-500$
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isThreeHundredChecked.value
                            ) {
                                isThreeHundredChecked.value = it
                                if (isThreeHundredChecked.value) {
                                    isLessThanChecked.value = false
                                    isOneHundredChecked.value = false
                                    isFiveHundredChecked.value = false
                                    isMoreThanChecked.value = false
                                    priceFrom.value = "301"
                                    priceTo.value = "500"
                                } else {
                                    priceFrom.value = ""
                                    priceTo.value = ""
                                }
                                if (priceFrom.value.isDigitsOnly() &&
                                    priceTo.value.isDigitsOnly()
                                ) {
                                    viewModel.onProductsEvent(
                                        FilterTypeEvent.IsPriceEvent(
                                            priceTo = 301.0,
                                            priceFrom = 500.0
                                        )
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string._301_500),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
//                        501-1000$
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isFiveHundredChecked.value
                            ) {
                                isFiveHundredChecked.value = it
                                if (isFiveHundredChecked.value) {
                                    isLessThanChecked.value = false
                                    isOneHundredChecked.value = false
                                    isThreeHundredChecked.value = false
                                    isMoreThanChecked.value = false
                                    priceFrom.value = "501"
                                    priceTo.value = "1000"
                                } else {
                                    priceFrom.value = ""
                                    priceTo.value = ""
                                }
                                if (priceFrom.value.isDigitsOnly() &&
                                    priceTo.value.isDigitsOnly()
                                ) {
                                    viewModel.onProductsEvent(
                                        FilterTypeEvent.IsPriceEvent(
                                            priceTo = 501.0,
                                            priceFrom = 1000.9
                                        )
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string._501_1000),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
//                        More than 1001$
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            DefaultCheckbox(
                                isChecked = isMoreThanChecked.value
                            ) {
                                isMoreThanChecked.value = it
                                if (isMoreThanChecked.value) {
                                    isLessThanChecked.value = false
                                    isOneHundredChecked.value = false
                                    isThreeHundredChecked.value = false
                                    isFiveHundredChecked.value = false
                                    priceFrom.value = "1001"
                                    priceTo.value = "37 000"
                                } else {
                                    priceFrom.value = ""
                                    priceTo.value = ""
                                }
                                if (priceFrom.value.isDigitsOnly() &&
                                    priceTo.value.isDigitsOnly()
                                ) {
                                    viewModel.onProductsEvent(
                                        FilterTypeEvent.IsPriceEvent(
                                            priceTo = 1001.0,
                                            priceFrom = 37000.0
                                        )
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string._1001),
                                    color = if (extractMainColor() == Color.Black) Color.White else Color.Black,
                                    fontSize = 19.sp
                                )
                            }
                        }
                    }
                }
                if (isBrandClicked.value) {
                    Column {
                        Text(
                            text = stringResource(R.string.is_have_reviews),
                            color = if (extractPrimaryMainColor() == Color.Black) Color.White else Color.Black,
                            fontSize = 22.sp
                        )
                        LazyColumn {
                            items(products.value){productsItem ->
                                FilterViewBrandItem(
                                    model = productsItem,
                                    color = extractPrimaryMainColor(),
                                    isChecked = isBrandChecked.value,
                                    onValueChange = {
                                        isBrandChecked.value
                                        if (isBrandChecked.value){
                                            productsItem.brand?.let { it1 -> brands.add(it1) }
                                            viewModel.onProductsEvent(FilterTypeEvent.BrandsEvent(brands))
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    if (isStateChanged.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .background(extractPrimaryMainColor())
            ) {
//                Button reset
                Button(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .height(45.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (extractMainColor() == darkerGray) Color.White else Color.Black
                    ),
                    onClick = { viewModel.resetFilter() }
                ) {
                    Text(
                        text = stringResource(R.string.reset),
                        color = if (extractMainColor() == darkerGray) Color.Black else Color.White,
                        fontSize = 18.sp
                    )
                }
//                button apply
                Button(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .height(45.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    ),
                    onClick = {
                        viewModel.onProductsEvent(FilterTypeEvent.FilterUtils(true))
                        navController.popBackStack()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.apply),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceFields(
    priceFrom: String,
    priceTo: String,
    onPriceFromSend: () -> Unit,
    onPriceToSend: () -> Unit,
    onPriceFromValueChange: (String) -> Unit,
    onPriceToValueChange: (String) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarState = rememberBottomSheetScaffoldState()

    val keyBoardController = LocalSoftwareKeyboardController.current
    val keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
//        Text field price from
        DefaultOutlinedTextFiled(
            value = priceFrom,
            onValueChange = {
                if (priceFrom.isDigitsOnly()) {
                    onPriceFromValueChange(it)
                } else {
                    coroutineScope.launch {
                        snackbarState.snackbarHostState.showSnackbar(
                            message = context.getString(R.string.the_price_field_must_not_have_any_letters)
                        )
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = {
                onPriceFromSend()
                keyBoardController?.hide()
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                disabledContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledBorderColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black
            ),
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 17.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.from),
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            },
            trailingIcon = {},
            leadingIcon = {}
        )
//        Text field price to
        DefaultOutlinedTextFiled(
            value = priceTo,
            onValueChange = {
                if (priceTo.isDigitsOnly()) {
                    onPriceToValueChange(it)
                } else {
                    coroutineScope.launch {
                        snackbarState.snackbarHostState.showSnackbar(
                            message = context.getString(R.string.the_price_field_must_not_have_any_letters)
                        )
                    }
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = {
                onPriceToSend()
                keyBoardController?.hide()
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                disabledContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledBorderColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black
            ),
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(),
            shape = RoundedCornerShape(10.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 17.sp
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.to),
                    color = Color.LightGray,
                    fontSize = 16.sp
                )
            },
            trailingIcon = {},
            leadingIcon = {}
        )
    }
}

@Composable
fun extractMainColor(): Color {

    return if (isSystemInDarkTheme()) {
        darkerGray
    } else {
        Color.White
    }
}

@Composable
fun extractPrimaryMainColor(): Color {
    return if (extractMainColor() == darkerGray) {
        Color.Black
    } else {
        Color.LightGray
    }
}