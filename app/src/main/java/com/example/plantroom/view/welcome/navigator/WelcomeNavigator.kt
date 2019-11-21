package com.example.plantroom.view.welcome.navigator

import com.example.plantroom.view.base.BaseNavigator

interface WelcomeNavigator : BaseNavigator{
    fun createAccount()

    fun login()

    fun isLogined()
}
