package com.emirhanuyunmaz.kotlincryptocurrency.Model

import com.google.gson.annotations.SerializedName


data class CryptoDetailModel(

    @SerializedName("id")
    val id:String?,

    @SerializedName("name")
    val name:String?,

    @SerializedName("symbol")
    val symbol:String,

    @SerializedName("rank")
    val rank:Int?,

    @SerializedName("is_new")
    val is_new:Boolean?,

    @SerializedName("is_active")
    val is_active:Boolean?,

    @SerializedName("type")
    val type:String?,

    @SerializedName("logo")
    val logo:String?,

    @SerializedName("description")
    val description:String?,

    @SerializedName("links")
    val links: Links?


) {

}