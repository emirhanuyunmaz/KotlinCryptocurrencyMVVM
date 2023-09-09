package com.emirhanuyunmaz.kotlincryptocurrency.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.emirhanuyunmaz.kotlincryptocurrency.Constant.DATABASE_NAME
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CyriptoModel

@Database(entities = arrayOf(CyriptoModel::class), version = 1)
abstract class CryptoDatabase :RoomDatabase() {
    abstract fun cryptoDao():CryptoDao


    companion object{
        @Volatile private var instance:CryptoDatabase?=null

        private val lock=Any()

        operator fun invoke(context: Context)= instance ?: synchronized(lock){
            instance ?: CryptoDatabase.makeDatabase(context).also {
                instance=it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            CryptoDatabase::class.java , DATABASE_NAME
        ).build()
    }


}