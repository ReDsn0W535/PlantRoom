package com.example.plantroom.utils

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

class BindingUtils private constructor(){
    companion object{
        @BindingAdapter("app:onCheckedChange")
        @JvmStatic
        fun onCheckedChange(editText: EditText, textWatcher: TextWatcher){
            editText.addTextChangedListener(textWatcher)
        }
    }
}