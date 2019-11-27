package com.example.plantroom.view.sign_in.sign_in.data

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantroom.R
import com.example.plantroom.repository.data.User
import com.example.plantroom.repository.firebase.FirebaseAuthRepository
import com.example.plantroom.view.base.BaseViewModel
import com.example.plantroom.view.sign_in.create_account.data.CombinedLiveData
import com.example.plantroom.view.sign_in.sign_in.navigator.SignInNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    context: Context,
    var firebaseAuthRepository: FirebaseAuthRepository
) :
    BaseViewModel<SignInNavigator>(context) {

    var pass = MutableLiveData<String>()
    var email = MutableLiveData<String>()

    var isReadyToLogin = CombinedLiveData<Boolean, String>(
        email to this::isEmailValid,
        pass to { pass -> (pass.length >= 8) },
        initValue = false
    ) { dataArray ->
        !dataArray.contains(false)
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

    fun loginWithEmailAndPassword() {
        viewModelScope.launch {
            var loginResult =
                withContext(Dispatchers.Default) {
                    firebaseAuthRepository.loginWithCredentials(
                        User(
                            name = "",
                            password = pass.value!!,
                            email = email.value!!
                        )
                    )
                }
            if (loginResult.sucess)
                getNavigator()?.goToMainMarket()
            else Toast.makeText(
                context,
                loginResult.errorMessage,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun getSignInClient(): Intent? = firebaseAuthRepository.getLoginIntent(
        context.getString(R.string.default_web_client_id),
        context
    )

    fun loginWithGoogle(intent: Intent) = viewModelScope.launch {
        var result =withContext(Dispatchers.IO){
            firebaseAuthRepository.getSignedInGoogleAccount(intent)
        }
        if (result.sucess)
            getNavigator()?.goToMainMarket()
        else Toast.makeText(
            context,
            result.errorMessage,
            Toast.LENGTH_LONG
        ).show()
    }
}