package com.emirhanuyunmaz.kotlincryptocurrency.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.emirhanuyunmaz.kotlincryptocurrency.Database.CryptoDatabase
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CyriptoModel
import com.emirhanuyunmaz.kotlincryptocurrency.Service.CyriptoApiClient
import com.emirhanuyunmaz.kotlincryptocurrency.Service.CyriptoService
import com.emirhanuyunmaz.kotlincryptocurrency.TimeSharedPreference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(var myapplication: Application):AndroidViewModel(myapplication) {

    private lateinit var cyriptoService: CyriptoService
    private var disposable= CompositeDisposable()
    private val refreshTime=10 * 60 * 1000 * 1000 * 1000L
    var cyriptoList=MutableLiveData<List<CyriptoModel>>()
    var loading=MutableLiveData<Boolean>()
    var errorList=MutableLiveData<Boolean>()

    fun getData(){

        var update=TimeSharedPreference.getTime()
        if(update!=null && update!=0L && System.nanoTime()-update<refreshTime){
            Toast.makeText(myapplication.applicationContext, "Database", Toast.LENGTH_SHORT).show()
            getDataFromDatabase()
        }else{
            Toast.makeText(myapplication.applicationContext, "Api", Toast.LENGTH_SHORT).show()
            getDataFromAPI()
        }

    }

    fun getDataFromAPI(){
        errorList.value=false
        loading.value=true
        cyriptoService= CyriptoApiClient.getCryptoClient().create(CyriptoService::class.java)
        disposable.add(cyriptoService.getCyripto()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CyriptoModel>>(){
                override fun onSuccess(t: List<CyriptoModel>) {
                    cyriptoList.value=t
                    databaseSave(t)
                    loading.value=false
                    errorList.value=false
                    TimeSharedPreference.saveTime(System.nanoTime())
                }

                override fun onError(e: Throwable) {
                    errorList.value=true
                    println(e)
                }
            }))
    }

    fun getDataFromDatabase(){

        loading.value=true
        errorList.value=false

        CoroutineScope(Dispatchers.IO).launch {
            var oldCryptoList=CryptoDatabase.invoke(myapplication).cryptoDao().getAllData()

            withContext(Dispatchers.Main){
                cyriptoList.value=oldCryptoList
            }
        }

    }

    private fun databaseSave(list: List<CyriptoModel>){
        CoroutineScope(Dispatchers.IO).launch {
            var cryptoListDao=CryptoDatabase.invoke(myapplication).cryptoDao()
            for (item in list){
                cryptoListDao.insert(item)
            }
            withContext(Dispatchers.Main){
                cyriptoList.value=cryptoListDao.getAllData()
            }
        }
    }


}