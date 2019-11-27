package com.example.plantroom.repository.firebase

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.plantroom.repository.data.Result
import com.example.plantroom.repository.data.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject


class FirebaseAuthRepository @Inject constructor(var auth: FirebaseAuth) {
    fun isLoginedAlready() = auth.currentUser != null

    suspend fun createUserWithCredentials(user: User): Result =
        try {
            Log.e("create user", "create account with credentials started")
            var createAccountTask =
                Tasks.await(auth.createUserWithEmailAndPassword(user.email, user.password))
            Log.e("create user", "create account with credentials finished")
            Result(true, result = createAccountTask.user, errorMessage = null)
        } catch (e: Exception) {
            Result(false, result = null, errorMessage = e.message)
        }

    suspend fun loginWithCredentials(user: User): Result =
        try {
            Log.e("signIn user", "sign in with credentials started")
            var signInTask =
                Tasks.await(auth.signInWithEmailAndPassword(user.email, user.password))
            Log.e("signIn user", "sign in with credentials finished")
            Result(true, result = signInTask.user, errorMessage = null)
        } catch (e: Exception) {
            Result(false, result = null, errorMessage = e.message)
        }

    fun getLoginIntent(client: String, context: Context): Intent? {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(client)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, gso).signInIntent
    }

    suspend fun getSignedInGoogleAccount(intent: Intent): Result {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        return try { // Google Sign In was successful, authenticate with Firebase
            val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
            firebaseLoginWithGoogle(account)
        } catch (e: ApiException) { // Google Sign In failed, update UI appropriately
            Log.w("getSignedInGoogle", "Google sign in failed", e)
            Result(false, null, e.message)
        }
    }

    private fun firebaseLoginWithGoogle(acct: GoogleSignInAccount?): Result {
        Log.d("firebaseLoginWithGoogle", "firebaseAuthWithGoogle:" + acct?.id)
        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        return try {
            val task = Tasks.await(auth.signInWithCredential(credential))
            Result(true, task, null)
        } catch (e: Exception) {
            Result(false, null, e.message)
        }
    }
}