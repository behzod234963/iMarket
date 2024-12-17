package com.mr.anonym.imarket.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.mr.anonym.data.local.dataStore.DataStoreInstance
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.Screens
import com.mr.anonym.imarket.presentation.utils.component.ConverterCoordinates
import com.mr.anonym.imarket.presentation.utils.component.GetFusedLocation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter",
    "CoroutineCreationDuringComposition"
)
@Composable
fun ChangeCityScreen(
    navController: NavHostController,
) {

    val context = LocalContext.current
    val activityContext = LocalContext.current as Activity
    val coroutineScope = rememberCoroutineScope()

    val dataStoreInstance = DataStoreInstance(context)

    val fusedLocation = GetFusedLocation(context, activityContext)
    val latitude = remember { mutableDoubleStateOf(fusedLocation.getLocation().first) }
    val longitude = remember { mutableDoubleStateOf(fusedLocation.getLocation().second) }

    val bottomSheetState = rememberModalBottomSheetState()
    val scaffoldBottomSheetState = rememberBottomSheetScaffoldState()

    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude.doubleValue, longitude.doubleValue), 5f)
    }
    val mapUiSettings = remember {
        mutableStateOf(
            MapUiSettings(
                zoomControlsEnabled = false
            )
        )
    }
    val isMapClicked = remember { mutableStateOf(false) }
    val isBottomSheetHidden = remember { mutableStateOf( false ) }
    val mapProperties = remember {
        mutableStateOf(
            MapProperties(
                isTrafficEnabled = true,
                mapType = MapType.NORMAL
            )
        )
    }

    Scaffold(
        floatingActionButton = {
//            FloatingActionButton(
//                modifier = Modifier
//                    .size(56.dp),
//                containerColor = Color.Magenta,
//                shape = RoundedCornerShape(56.dp),
//                onClick = {
//                    latitude.doubleValue = fusedLocation.getLocation().first
//                    longitude.doubleValue = fusedLocation.getLocation().second
//                }
//            ) {
//                Icon(
//                    modifier = Modifier
//                        .padding(10.dp),
//                    painter = painterResource(R.drawable.ic_location),
//                    contentDescription = "my location"
//                )
//            }
        }
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraState,
            properties = mapProperties.value,
            uiSettings = mapUiSettings.value,
            onMapClick = {mapClick->
                isMapClicked.value = true
                latitude.doubleValue = mapClick.latitude
                longitude.doubleValue = mapClick.longitude
            },
        ) {
            if (isMapClicked.value) {

                val geoCoder = ConverterCoordinates(
                    context,
                    latitude.doubleValue,
                    longitude.doubleValue
                ).converterGeoCode().also {
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                latitude.doubleValue,
                                longitude.doubleValue
                            )
                        ),
                        visible = if (isMapClicked.value) true else false,
                        title = it,
                        snippet = stringResource(R.string.location),
                    )
                }
                coroutineScope.launch {
                    if (isMapClicked.value){
                        scaffoldBottomSheetState.bottomSheetState.show()
                    }
                }
                BottomSheet(
                    bottomSheetState,
                    coroutineScope,
                    geoCoder,
                    onConfirmClick = {
                        coroutineScope.launch {
                            if (!geoCoder.contains(context.getString(R.string.unknown))){
                                dataStoreInstance.saveCity(geoCoder)
                                bottomSheetState.hide()
                                navController.navigate(Screens.MainScreen.route)
                            }else{
                                scaffoldBottomSheetState.snackbarHostState.showSnackbar(
                                    context.getString(R.string.unknown_location)
                                )
                            }
                        }
                    },
                    isBottomSheetHidden={
                        isBottomSheetHidden.value = it
                        isMapClicked.value = !it
                    })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    bottomSheetState:SheetState,
    coroutineScope: CoroutineScope,
    city: String,
    onConfirmClick:()->Unit,
    isBottomSheetHidden:(Boolean)->Unit
) {

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = {
            coroutineScope.launch {
                bottomSheetState.hide()
                isBottomSheetHidden(true)
            }
        },
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.your_city),
                color = Color.Black,
                fontSize = 18.sp
            )
            Text(
                text = city,
                color = Color.Black,
                fontSize = 22.sp
            )
             Column(
                 modifier = Modifier
                     .fillMaxWidth(),
                 verticalArrangement = Arrangement.Bottom
             ) {
                 Button(
                     modifier = Modifier
                         .padding(top = 20.dp)
                         .height(50.dp)
                         .fillMaxWidth(),
                     colors = ButtonDefaults.buttonColors(
                         containerColor = Color.Red,
                         contentColor = Color.Red,
                     ),
                     elevation = ButtonDefaults.buttonElevation(5.dp),
                     onClick = {
                         onConfirmClick()
                     }
                 ) {
                     Text(
                         text = stringResource(R.string.confirm_city),
                         color = Color.White,
                         fontSize = 18.sp
                     )
                 }
             }
        }
    }
}