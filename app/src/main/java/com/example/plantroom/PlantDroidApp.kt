package com.example.plantroom

import android.app.Application
import com.example.plantroom.dagger2.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


open class PlantRoomApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .context(this)
            .build()
            .inject(this)


    }

    //fun getAppComponent() = appComponent
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}