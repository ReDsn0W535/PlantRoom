package com.example.plantroom.view.base.uiLifecycleScope

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class UiLifecycleScope : CoroutineScope, LifecycleObserver {

    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        job = Job()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() = job.cancel()
}