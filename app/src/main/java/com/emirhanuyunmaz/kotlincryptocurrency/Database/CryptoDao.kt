package com.emirhanuyunmaz.kotlincryptocurrency.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emirhanuyunmaz.kotlincryptocurrency.Model.CyriptoModel

@Dao
interface CryptoDao {

    @Insert
    fun insert(vararg cyriptoModel: CyriptoModel)

    @Query("SELECT * FROM crypto_table")
    fun getAllData():List<CyriptoModel>

    @Query("DELETE FROM crypto_table")
    fun deleteAll()

}