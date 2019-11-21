package com.example.plantroom.view.sign_in.create_account.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class CombinedLiveData<S, T>(
    vararg data: Pair<LiveData<T>, (var1: T) -> S>,
    initValue: S,
    combineFunc: (List<S>) -> S
) :  //bool, string
    MediatorLiveData<S>() {
    init {
        value = initValue
        var dataArray = ArrayList<S>()
        for (item in data)
            dataArray.add(initValue)
        data.forEach {
            addSource(it.first) { inputString ->
                dataArray[data.indexOf(it)] = it.second(inputString)
                value = combineFunc(dataArray)
            }
        }
    }
}