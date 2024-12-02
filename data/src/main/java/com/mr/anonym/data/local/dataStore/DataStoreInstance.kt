package com.mr.anonym.data.local.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "iMarket")

class DataStoreInstance(private val context: Context) {

    suspend fun saveLatitude(latitude:Double){
        val doubleKey = doublePreferencesKey("latitude")
        context.dataStore.edit {result->
            result[doubleKey] = latitude
        }
    }
    fun getLatitude(): Flow<Double> {
        val doubleKey = doublePreferencesKey("latitude")
        return context.dataStore.data.map { result->
            result[doubleKey]?:0.0
        }
    }
    suspend fun saveLongitude(longitude:Double){
        val doubleKey = doublePreferencesKey("longitude")
        context.dataStore.edit {result->
            result[doubleKey] = longitude
        }
    }
    fun getLongitude(): Flow<Double> {
        val doubleKey = doublePreferencesKey("longitude")
        return context.dataStore.data.map { result->
            result[doubleKey]?:0.0
        }
    }
    suspend fun saveCity(city:String){
        val stringKey = stringPreferencesKey("city")
        context.dataStore.edit {result->
            result[stringKey] = city
        }
    }
    fun getCity(): Flow<String> {
        val stringKey = stringPreferencesKey("city")
        return context.dataStore.data.map { result->
            result[stringKey]?:""
        }
    }
    suspend fun saveLocationStatus(status:Boolean){
        val booleanKey = booleanPreferencesKey("location status")
        context.dataStore.edit {result->
            result[booleanKey] = status
        }
    }
    fun getLocationStatus(): Flow<Boolean> {
        val booleanKey = booleanPreferencesKey("location status")
        return context.dataStore.data.map { result->
            result[booleanKey]?:false
        }
    }
    suspend fun saveIsFirstLaunch(status:Boolean){
        val booleanKey = booleanPreferencesKey("IsFirstLaunch")
        context.dataStore.edit {result->
            result[booleanKey] = status
        }
    }
    fun getIsFirstLaunch(): Flow<Boolean> {
        val booleanKey = booleanPreferencesKey("IsFirstLaunch")
        return context.dataStore.data.map { result->
            result[booleanKey]?:false
        }
    }
}