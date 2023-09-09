package com.emirhanuyunmaz.kotlincryptocurrency

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadFromUrl(url:String,progressDrawable: CircularProgressDrawable){
    val options=RequestOptions().placeholder(progressDrawable).error(R.drawable.baseline_error_24)
    Glide.with(context).load(url).into(this)
}

fun placesHolderProgressBar(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply{
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}