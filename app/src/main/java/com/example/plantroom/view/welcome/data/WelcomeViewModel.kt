package com.example.plantroom.view.welcome.data

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.plantroom.view.base.BaseViewModel
import com.example.plantroom.repository.bd.PrefsRepository
import com.example.plantroom.repository.firebase.FirebaseAuthRepository
import com.example.plantroom.view.welcome.navigator.WelcomeNavigator
import javax.inject.Inject

class WelcomeViewModel @Inject constructor(
    context: Context,
    var prefsRepository: PrefsRepository,
    var firebaseAuthRepository: FirebaseAuthRepository
) :
    BaseViewModel<WelcomeNavigator>(context) {

    fun signIn() {
        getNavigator()?.login()
    }

    fun createAccount() {
        getNavigator()?.createAccount()
    }

    private fun checkForLogin() {
        if (firebaseAuthRepository.isLoginedAlready())
            getNavigator()?.isLoginedAlready()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun checkOnLogin() {
        if (prefsRepository.isUserLogon())
            checkForLogin()
    }

}