package com.example.plantroom.view.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.lang.ref.WeakReference

abstract class BaseViewModel<T : BaseNavigator>(var context: Context) : AndroidViewModel(context as Application), LifecycleObserver{
    private var navigator : WeakReference<T>? = null

    fun setNavigator(navigator : T){
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator() = navigator.let { it?.get() }
}