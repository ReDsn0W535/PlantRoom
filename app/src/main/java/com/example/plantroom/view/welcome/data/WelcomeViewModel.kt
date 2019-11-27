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
    private var prefsRepository: PrefsRepository,
    private var firebaseAuthRepository: FirebaseAuthRepository
) :
    BaseViewModel<WelcomeNavigator>(context) {

    fun signIn() {
        getNavigator()?.login()
    }

    fun createAccount() {
        getNavigator()?.createAccount()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun isLoginedAlready(): Boolean = firebaseAuthRepository.isLoginedAlready()
}
