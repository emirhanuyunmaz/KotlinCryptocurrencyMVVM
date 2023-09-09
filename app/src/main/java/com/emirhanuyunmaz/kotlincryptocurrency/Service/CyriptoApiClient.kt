package com.emirhanuyunmaz.kotlincryptocurrency.Service

import com.emirhanuyunmaz.kotlincryptocurrency.Constant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CyriptoApiClient {
    var retrofit : Retrofit?=null

    //RxJava2CallAdapterFactory--> Single -> create...
    fun getCryptoClient(): Retrofit {
        if(retrofit==null){
            retrofit= Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(
                GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            return retrofit as Retrofit
        }else{
            return retrofit as Retrofit
        }
    }



}