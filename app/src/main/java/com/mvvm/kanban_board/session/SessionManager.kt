package com.mvvm.kanban_board.session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object SessionManager {

    private const val USER_ID = "USER_ID"
    private const val USER_NAME = "USER_NAME"
    private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    private const val REFRESH_TOKEN = "REFRESH_TOKEN"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    var accessToken: String?
        get() = preferences.getString(ACCESS_TOKEN, "")
        set(value) {
            preferences.edit().putString(ACCESS_TOKEN, value).apply()
        }


    var refreshToken: String?
        get() = preferences.getString(ACCESS_TOKEN, "")
        set(value) {
            preferences.edit().putString(ACCESS_TOKEN, value).apply()
        }

    //logged in user during current session

    var username: String?
    get() = preferences.getString(USER_NAME, "")
    set(value) {
        preferences.edit().putString(USER_NAME, value).apply()
    }

    var userID: String?
        get() = preferences.getString(USER_ID, "")
        set(value) {
            preferences.edit().putString(USER_ID, value).apply()
        }


//    val userIDString: String?
//        get() = preferences.getString(USER_ID, "")
//    var userID: Long
//        get() {
//            val userID = preferences.getString(USER_ID, "")
//            return userID.toLong()
//        }
//        set(value) {
//            preferences.edit().putString(USER_ID, value.toString()).apply()
//        }


//
//    fun logout(context: Context) {     //used also when token is deprecated
//        preferences.edit().clear().apply()
//         //show login screen
//    }
//

}
