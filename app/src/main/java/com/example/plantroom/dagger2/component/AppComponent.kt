package com.example.plantroom.dagger2.component

import android.content.Context
import com.example.plantroom.PlantRoomApp
import com.example.plantroom.dagger2.module.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: PlantRoomApp?)
}