package com.example.plantroom.repository.firebase

import android.util.Log
import com.example.plantroom.repository.data.AuthentificationResult
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(var auth: FirebaseAuth) {
    fun isLoginedAlready() = auth.currentUser != null

    suspend fun createUserWithCredentials(email: String, password: String): AuthentificationResult =
        withContext(Dispatchers.IO) {
            try {
                Log.e("create user", "create account with credentials started")
                var task = Tasks.await(auth.createUserWithEmailAndPassword(email, password))
                AuthentificationResult(true, authResult = task, errorMessage = null)
            } catch (e: Exception) {
                AuthentificationResult(true, authResult = null, errorMessage = e.message)
            }
        }
}