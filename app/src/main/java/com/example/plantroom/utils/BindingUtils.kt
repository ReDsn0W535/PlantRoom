package com.example.plantroom.utils

import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class BindingUtils private constructor() {
    companion object {
        @BindingAdapter("app:onCheckedChange")
        @JvmStatic
        fun onCheckedChange(editText: EditText, textWatcher: TextWatcher) {
            editText.addTextChangedListener(textWatcher)
        }

        @JvmStatic
        @BindingAdapter("app:url")
        fun loadImage(view: ImageView, url: String) {
            Picasso.get().load(url).into(view)
        }
    }
}