package com.emirhanuyunmaz.kotlincryptocurrency.ViewModel

import android.app.Application
import android.telecom.Call.Details
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CryptoDetailModel
import com.emirhanuyunmaz.kotlincryptocurrency.Service.CyriptoApiClient
import com.emirhanuyunmaz.kotlincryptocurrency.Service.CyriptoService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailActivityViewModel(myApplication: Application):AndroidViewModel(myApplication) {

    private val disposable=CompositeDisposable()
    var cryptoDetail=MutableLiveData<CryptoDetailModel>()
    private lateinit var cyriptoApi:CyriptoService


    fun getDataFromApi(id:String){
        cyriptoApi=CyriptoApiClient.getCryptoClient().create(CyriptoService::class.java)
        disposable.add(cyriptoApi.getCryptoDetail(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CryptoDetailModel>(){
                override fun onSuccess(t: CryptoDetailModel) {
                    cryptoDetail.value=t
                }

                override fun onError(e: Throwable) {
                    println(e.localizedMessage)
                }
            }))
    }


}