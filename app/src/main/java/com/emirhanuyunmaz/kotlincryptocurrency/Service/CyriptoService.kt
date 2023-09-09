package com.emirhanuyunmaz.kotlincryptocurrency.Service

import com.emirhanuyunmaz.kotlincryptocurrency.Model.CryptoDetailModel
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CyriptoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CyriptoService {

    //Single-->rxandroid
    @GET("v1/coins")
    fun getCyripto(): Single<List<CyriptoModel>>

    @GET("v1/coins/{id}")
    fun getCryptoDetail(@Path("id") id: String?): Single<CryptoDetailModel>

}