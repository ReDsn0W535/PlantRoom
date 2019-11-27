package com.example.plantroom.dagger2.module.modules

import android.content.SharedPreferences
import com.example.plantroom.repository.bd.PrefsRepository
import com.example.plantroom.repository.firebase.FirebaseAuthRepository
import com.example.plantroom.repository.firebase.FirebaseCloudStorageRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
    @Provides
    fun provideCloudStorageRepository(reference : FirebaseStorage) : FirebaseCloudStorageRepository{
        return FirebaseCloudStorageRepository(reference)
    }
}