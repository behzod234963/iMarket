package com.mr.anonym.imarket.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.Screens
import com.mr.anonym.imarket.presentation.viewModel.MainViewModel
import com.mr.anonym.imarket.ui.theme.fontAmidoneGrotesk

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController:NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activityContext = LocalContext.current as Activity
    val coroutineScope = rememberCoroutineScope()

    val city = viewModel.city.value

    Scaffold (
        topBar = {
            MediumTopAppBar(
//                expandedHeight = 70.dp,
//                collapsedHeight = 70.dp,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red
                ),
                navigationIcon = {
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = stringResource(R.string.app_name),
                        fontFamily = FontFamily(fontAmidoneGrotesk),
                        fontSize = 22.sp,
                        color = Color.White
                    )
                },
                actions = {
                    FlowRow {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = "icon location",
                            tint = Color.White
                        )
                        Text(
                            modifier = Modifier
                                .padding(end = 10.dp),
                            text = city,
                            fontFamily = FontFamily(fontAmidoneGrotesk),
                            fontSize = 22.sp,
                            color = Color.White
                        )
                    }
                },
                title = {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalArrangement = Arrangement.Center,
                        maxItemsInEachRow = 2
                    ) {
                        OutlinedTextField(
                            enabled = false,
                            value = "",
                            onValueChange = {},
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledContainerColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentHeight()
                                .padding(bottom = 5.dp)
                                .clickable {
                                    navController.navigate(Screens.SearchFieldScreen.route)
                                },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "icon search field",
                                    tint = Color.LightGray
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        TODO()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(25.dp),
                                        painter = painterResource(R.drawable.ic_qr_code),
                                        contentDescription = "check qr code screen",
                                        tint = Color.LightGray
                                    )
                                }
                            }
                        )
                        Card (
                            modifier = Modifier
                                .height(50.dp)
                                .padding(end = 5.dp, bottom = 5.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ){
                            IconButton(
                                onClick = {
                                    TODO()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "notification screen button",
                                    tint = Color.LightGray
                                )
                            }
                        }
                    }
                },
            )
        }
    ){}
}