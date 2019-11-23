package com.example.plantroom.view.sign_in.create_account.data

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.plantroom.repository.firebase.FirebaseAuthRepository
import com.example.plantroom.repository.data.User
import com.example.plantroom.view.base.BaseViewModel
import com.example.plantroom.view.sign_in.create_account.navigator.CreateAccountNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern
import javax.inject.Inject

class CreateAccountViewModel @Inject constructor(
    context: Context, var firebaseAuthRepository: FirebaseAuthRepository
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

    fun loginWithGoogle() = viewModelScope.launch {

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

    fun registerWithEmailAndPassword() {
        var user = User(name = username.value, password = password.value, email = email.value)
        var authResult = viewModelScope.async {
            return@async user.email.let {
                user.password.let { it1 ->
                    firebaseAuthRepository.createUserWithCredentials(
                        email = it!!,
                        password = it1!!
                    )
                }
            }

        }
        viewModelScope.launch {
            if (authResult.await().sucess)
                withContext(Dispatchers.Main) {
                    getNavigator()?.goToQuiz()
                }
            else Toast.makeText(context, authResult.getCompleted().errorMessage, Toast.LENGTH_LONG).show()
        }
    }


}