package com.example.plantroom.view.quiz.ui

import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import com.example.plantroom.R
import com.example.plantroom.ViewModelProviderFactory
import com.example.plantroom.databinding.QuizFragmentBinding
import com.example.plantroom.view.base.BaseFragment
import com.example.plantroom.view.quiz.data.QuizViewModel
import com.example.plantroom.view.quiz.navigator.QuizNavigator
import javax.inject.Inject

class QuizFragment(layout : Int) : BaseFragment<QuizFragmentBinding,QuizViewModel>(layout), QuizNavigator {

    @Inject
    internal lateinit var factory : ViewModelProviderFactory
    private lateinit var viewModel : QuizViewModel
    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.quiz_fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun getViewModel(): QuizViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(QuizViewModel::class.java)
        return viewModel
    }
}