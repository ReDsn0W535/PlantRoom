package com.example.plantroom.view.welcome.data

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.plantroom.view.base.BaseViewModel
import com.example.plantroom.repository.bd.PrefsRepository
import com.example.plantroom.view.welcome.navigator.WelcomeNavigator
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    context: Context, var prefsRepository: PrefsRepository
) :
    BaseViewModel<WelcomeNavigator>(context) {

    public fun signIn() {
        getNavigator()?.login()
    }

    public fun createAccount() {
        getNavigator()?.createAccount()
    }

    private fun isLoginedAlready(){
        getNavigator()?.isLogined()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun checkOnLogin() {
        if (prefsRepository.isUserLogon())
            isLoginedAlready()
    }

}