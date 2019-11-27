package com.example.plantroom.repository.data

import com.google.firebase.auth.AuthResult
import java.lang.Exception

data class Result(var sucess : Boolean, var result: Any?, var errorMessage : String?) {
}