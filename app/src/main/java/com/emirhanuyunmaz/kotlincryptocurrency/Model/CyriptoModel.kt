package com.emirhanuyunmaz.kotlincryptocurrency.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("crypto_table")
data class CyriptoModel (

    @ColumnInfo("id")
    @SerializedName("id")
    val id:String?,

    @ColumnInfo("name")
    @SerializedName("name")
    val name:String?,

    @ColumnInfo("symbol")
    @SerializedName("symbol")
    val symbol:String?,

    @ColumnInfo("rank")
    @SerializedName("rank")
    val rank:Int?,

    @ColumnInfo("is_new")
    @SerializedName("is_new")
    val is_new:Boolean?,

    @ColumnInfo("is_active")
    @SerializedName("is_active")
    val is_active:Boolean?,

    @ColumnInfo("type")
    @SerializedName("type")
    val type:String?,

){

    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0

}