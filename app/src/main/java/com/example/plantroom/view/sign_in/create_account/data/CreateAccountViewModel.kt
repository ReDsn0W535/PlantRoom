package com.example.plantroom.view.sign_in.create_account.data

import android.content.Context
import androidx.lifecycle.*
import com.example.plantroom.repository.other.User
import com.example.plantroom.view.base.BaseViewModel
import com.example.plantroom.view.sign_in.create_account.navigator.CreateAccountNavigator
import java.util.regex.Pattern
import javax.inject.Inject

class CreateAccountViewModel @Inject constructor(
    context: Context
) : BaseViewModel<CreateAccountNavigator>(context) {

    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var isReadyToLogin = CombinedLiveData<Boolean, String>(
        email to this::isEmailValid,
        password to { pass -> (pass.length >= 8) },
        username to this::isUsernameValid,
        initValue = false
    ) { dataArray ->
        !dataArray.contains(false)
    }

    fun loginWithGoogle() {

    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun isEmailValid(email: String): Boolean {
        val regExpn = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|" +
                "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\" +
                "x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]" +
                "(?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25" +
                "[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:" +
                "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"

        var inputStr = StringBuilder(email)
        var pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(inputStr)
        return matcher.matches()
    }

    private fun isUsernameValid(name: String): Boolean {
        return true
    }

    fun register() {
        var user = User(name = username.value, password = password.value, email = email.value)

    }
}