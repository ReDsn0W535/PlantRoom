package com.example.plantroom.repository.data

import com.google.firebase.auth.AuthResult
import java.lang.Exception

data class AuthentificationResult(var sucess : Boolean,var authResult: AuthResult?, var errorMessage : String?) {
}