package com.example.plantroom.view.sign_in.create_account.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.plantroom.BR
import com.example.plantroom.R
import com.example.plantroom.ViewModelProviderFactory
import com.example.plantroom.databinding.CreateAccountFragmentBinding
import com.example.plantroom.view.base.BaseFragment
import com.example.plantroom.view.quiz.ui.QuizFragment
import com.example.plantroom.view.sign_in.create_account.data.CreateAccountViewModel
import com.example.plantroom.view.sign_in.create_account.navigator.CreateAccountNavigator
import com.example.plantroom.view.welcome.data.WelcomeViewModel
import javax.inject.Inject

class CreateAccountFragment(layout: Int) :
    BaseFragment<CreateAccountFragmentBinding, CreateAccountViewModel>(layout), CreateAccountNavigator {

    @Inject
    internal lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: CreateAccountViewModel

    override fun getBindingVariable() = BR.viewModel
    override fun getLayoutId() = R.layout.create_account_fragment

    override fun getViewModel(): CreateAccountViewModel {
        viewModel =
            ViewModelProviders.of(this, factory).get(CreateAccountViewModel::class.java)

        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun goToQuiz() {
        getBaseActivity().addFragment(QuizFragment(R.layout.quiz_fragment), "QuizFragment")
    }
}