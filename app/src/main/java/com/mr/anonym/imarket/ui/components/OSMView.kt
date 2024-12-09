//package com.mr.anonym.imarket.ui.views
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.util.Log
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Button
//import androidx.compose.material3.DatePicker
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableDoubleStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import com.mr.anonym.data.local.dataStore.DataStoreInstance
//import com.mr.anonym.imarket.presentation.utils.GetFusedLocation
//import kotlinx.coroutines.launch
//import org.osmdroid.tileprovider.tilesource.TileSourceFactory
//import org.osmdroid.util.GeoPoint
//import org.osmdroid.views.MapView
//
//@SuppressLint("CoroutineCreationDuringComposition")
//@Composable
//fun OSMView() {
//
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//    val dataStoreInstance = DataStoreInstance(context)
//    val latitude = remember { mutableDoubleStateOf(0.0) }
//    val longitude = remember { mutableDoubleStateOf(0.0) }
//    coroutineScope.launch{
//        dataStoreInstance.getLatitude().collect{ latitude.doubleValue = it }
//        dataStoreInstance.getLongitude().collect{ longitude.doubleValue = it }
//    }
//    val geoPoint = remember{ mutableStateOf( GeoPoint(latitude.doubleValue,longitude.doubleValue) ) }
//
//    AndroidView(
//        modifier = Modifier
//            .fillMaxSize(),
//        factory = {
//            MapView(context).apply {
//                setTileSource(TileSourceFactory.MAPNIK)
//                setBuiltInZoomControls(true)
//                setMultiTouchControls(true)
//                setExpectedCenter(geoPoint.value)
//                this.getMapCenter(geoPoint.value)
//                Log.d("LocationLogging", "OSMView: ${latitude.doubleValue},${longitude.doubleValue}")
//            }
//        },
//        update = {
//            it.controller.setCenter(geoPoint.value)
//        }
//    )
//}