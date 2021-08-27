package com.example.newsbreezeapp.database

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    //cache code
    private  val PREFERENCE_NAME = "users"
    private var sharedPreferences: SharedPreferences? = null

    val USER_ID = "USER_ID"
    fun getSharedPreferences(context: Context): SharedPreferences? {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        }
        return sharedPreferences
    }


    fun writeDataToPreference(key: String?, value: String?) {
        val editor = sharedPreferences!!.edit()
        editor.putString(key, value)
        editor.apply()
    }
    fun getDataToPreference(key: String?): String? {
        return sharedPreferences!!.getString(key, "")
    }
}