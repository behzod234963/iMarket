package com.mr.anonym.imarket.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.Screens
import com.mr.anonym.imarket.presentation.utils.ConverterCoordinates
import com.mr.anonym.imarket.presentation.utils.GetFusedLocation
import com.mr.anonym.imarket.presentation.utils.PermissionHandler

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingScreen(
    navController: NavHostController
) {

    val context = LocalContext.current
    val activityContext = LocalContext.current as Activity
    val locationAnimation =
        rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_location))

    val permissionHandler = PermissionHandler(context, activityContext)
    val fusedLocation = GetFusedLocation(context, activityContext)
    val geoCoder = ConverterCoordinates(
        context = context,
        latitude = fusedLocation.getLocation().first,
        longitude = fusedLocation.getLocation().second
    ).converterGeoCode()

    val isClicked = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isClicked.value) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                navController.navigate(Screens.ChangeCityScreen.route)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .padding(bottom = 30.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (!isClicked.value) {
                Box(
                    Modifier
                        .height(600.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LottieAnimation(
                        composition = locationAnimation.value,
                        iterations = LottieConstants.IterateForever,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = stringResource(R.string.your_city),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = geoCoder,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }

                Spacer(Modifier.height(20.dp))

//            Button confirm city
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Red,
                    ),
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    onClick = {
                        navController.navigate(Screens.MainScreen.route)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.confirm_city),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }
                }

                Spacer(Modifier.height(20.dp))

//            Button change city
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.White,
                    ),
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    border = BorderStroke(
                        width = 0.7.dp,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    ),
                    onClick = {
                        if (ActivityCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) ==
                            PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) ==
                            PackageManager.PERMISSION_GRANTED
                        ) {
                            isClicked.value = true
                        } else {
                            permissionHandler.locationRequest(permissionStatus = {
                                if (it) {
                                    isClicked.value = true
                                }
                            })
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.change_city),
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                }
            }

        }
    }
}