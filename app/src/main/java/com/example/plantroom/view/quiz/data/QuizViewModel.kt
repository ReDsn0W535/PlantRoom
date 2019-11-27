package com.example.plantroom.view.quiz.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantroom.R
import com.example.plantroom.repository.data.QuizVariant
import com.example.plantroom.repository.firebase.FirebaseCloudStorageRepository
import com.example.plantroom.view.base.BaseViewModel
import com.example.plantroom.view.quiz.navigator.QuizNavigator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowViaChannel
import java.util.Observer
import javax.inject.Inject

class QuizViewModel @Inject constructor(
    context: Context,
    var firebaseCloudStorageRepository: FirebaseCloudStorageRepository
) : BaseViewModel<QuizNavigator>(context) {

    var options = MutableLiveData<ArrayList<QuizVariant>>()

    fun optionStateChanged(variant: QuizVariant, state: Boolean) =
        run { if (state) options.value?.add(variant) else options.value?.remove(variant) }

    var tempDrawable = MutableLiveData<BitmapDrawable>()

    fun getPicture(view: View) {

    }

    init {
        viewModelScope.launch {
            tempDrawable.value =
                BitmapDrawable(
                    context.resources,
                    firebaseCloudStorageRepository.downloadFile(context.getString(R.string.quiz_home_address)).result as Bitmap
                )
        }
    }
}