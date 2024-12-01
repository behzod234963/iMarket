package com.mr.anonym.imarket.presentation.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.mr.anonym.data.local.dataStore.DataStoreInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetFusedLocation(private val context: Context, private val activity: Activity) {

    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)
    private val permissionHandler = PermissionHandler(context, activity)
    private var latitude = 0.0
    private var longitude = 0.0
    private val dataStoreInstance = DataStoreInstance(context)

    fun getLocation():Pair<Double,Double> {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionHandler.locationRequest {  }
        }else{
            fusedLocation.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                    CoroutineScope(Dispatchers.IO).launch {
                        dataStoreInstance.saveLatitude(latitude)
                        dataStoreInstance.saveLongitude(longitude)
                        Log.d("LocationLogging", "getLocation: $latitude , $longitude")
                    }
                }
            }
        }
        return Pair(latitude,longitude)
    }
}