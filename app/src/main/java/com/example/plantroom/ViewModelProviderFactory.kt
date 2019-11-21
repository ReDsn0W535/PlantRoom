package com.example.plantroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
public class ViewModelProviderFactory @Inject constructor(var creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = creators[modelClass]
        if (creator == null)
            creators.entries.forEach {
                if (modelClass.isAssignableFrom(it.key)) {
                    creator = it.value
                    return@forEach
                }
            }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class $modelClass");
        }
        return try {
            creator?.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e);
        }

    }
}


/*        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }*/
