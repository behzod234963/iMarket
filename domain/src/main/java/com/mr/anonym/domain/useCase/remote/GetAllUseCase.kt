package com.mr.anonym.domain.useCase.remote

import android.util.Log
import com.mr.anonym.domain.model.ProductsItem
import com.mr.anonym.domain.model.ProductsModel
import com.mr.anonym.domain.repository.remote.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAllUseCase(private val repository: ProductsRepository) {

    @OptIn(DelicateCoroutinesApi::class)
    fun execute(): Flow<List<ProductsItem>>  = channelFlow{

        val coroutineScope = CoroutineScope(Job()+Dispatchers.Default)
        repository.getAllProducts().enqueue(object:Callback<ProductsModel>{
            override fun onResponse(p0: Call<ProductsModel>, response: Response<ProductsModel>){
                val productsList = response.body()?.products
                if (productsList != null){
                    if (response.isSuccessful){
                        if (!channel.isClosedForSend){
                            coroutineScope.launch {
                                channel.send(productsList)
                                if (channel.isClosedForSend){
                                    coroutineScope.cancel()
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<ProductsModel>, p1: Throwable) {
                Log.d("NetworkLogging", "onFailure: GetAll data failed ${p1.message}")
            }
        })
    }
}
//
//Log.d(
//"NetworkLogging",
//"GetAllUseCaseExecuteCallback:data failed ${p1.message} "
//)