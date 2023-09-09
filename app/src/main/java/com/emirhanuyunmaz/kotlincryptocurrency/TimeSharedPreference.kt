package com.emirhanuyunmaz.kotlincryptocurrency

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class TimeSharedPreference {

    companion object{
        private val PREFERENCES_TIME="preferences_time"
        private var sharedPreferences:SharedPreferences?=null

        @Volatile private var instance:TimeSharedPreference?=null
        private val lock=Any()

        operator fun invoke(context: Context):TimeSharedPreference = instance ?: synchronized(lock){
            instance ?: makeTimeSharedPreferences(context).also {
                instance=it
            }
        }

        private fun makeTimeSharedPreferences(context: Context): TimeSharedPreference {
            sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context)
            return TimeSharedPreference()
        }

        fun saveTime(time:Long){
            sharedPreferences?.edit(commit = true){
                putLong(PREFERENCES_TIME,time)
            }
        }
        fun getTime()= sharedPreferences?.getLong(PREFERENCES_TIME,0L)
    }

}