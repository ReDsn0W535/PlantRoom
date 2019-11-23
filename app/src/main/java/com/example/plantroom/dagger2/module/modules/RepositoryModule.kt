package com.example.plantroom.dagger2.module.modules

import android.content.SharedPreferences
import com.example.plantroom.repository.bd.PrefsRepository
import com.example.plantroom.repository.firebase.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providePreferencesRepository(preferences: SharedPreferences) : PrefsRepository {
        return PrefsRepository(preferences)
    }
    @Provides
    fun provieAuthRepository(firebaseAuth: FirebaseAuth) : FirebaseAuthRepository{
        return FirebaseAuthRepository(firebaseAuth)
    }
}