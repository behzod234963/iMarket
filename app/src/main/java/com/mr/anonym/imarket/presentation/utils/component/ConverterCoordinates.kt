package com.mr.anonym.imarket.presentation.utils.component

import android.content.Context
import android.location.Geocoder
import com.mr.anonym.imarket.R
import java.util.Locale

class ConverterCoordinates(
    private val context: Context,
    private val latitude: Double,
    private val longitude: Double
) {
    private val geoCoder = Geocoder(context,Locale.getDefault())

    fun converterGeoCode():String{

        val addresses = geoCoder.getFromLocation(latitude,longitude,1)
        val address = if (addresses?.size != 0){
            addresses?.get(0)?.subAdminArea
        }else{
            context.getString(R.string.unknown)
        }
        return address.toString()
    }
}