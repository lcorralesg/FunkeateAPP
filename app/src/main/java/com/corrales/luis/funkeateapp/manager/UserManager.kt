package com.corrales.luis.funkeateapp.manager

import android.content.Context

class UserManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserData(userEmail: String, userName: String, pictureURL: String) {
        sharedPreferences.edit().apply {
            putString("user_email", userEmail)
            putString("user_name", userName)
            putString("picture_url", pictureURL)
            apply()
        }
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString("user_email", null)
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("user_name", null)
    }

    fun getPictureURL(): String? {
        return sharedPreferences.getString("picture_url", null)
    }

    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}
