package com.example.plantroom.dagger2.module.modules

import android.content.SharedPreferences
import com.example.plantroom.repository.bd.PrefsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providePreferencesRepository( preferences: SharedPreferences) : PrefsRepository {
        return PrefsRepository(preferences)
    }
}