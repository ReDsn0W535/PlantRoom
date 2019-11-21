package com.example.plantroom.repository.bd

import android.content.SharedPreferences


class PrefsRepository(var preferences: SharedPreferences) {
    fun setLoginVar(isLogin : Boolean): Boolean = preferences.edit().putBoolean("isLoginComplete", isLogin).commit()
    fun isUserLogon() = preferences.getBoolean("isLoginComplete", false)
}