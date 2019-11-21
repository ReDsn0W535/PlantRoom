package com.example.plantroom.view.sign_in.create_account.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.plantroom.BR
import com.example.plantroom.R
import com.example.plantroom.ViewModelProviderFactory
import com.example.plantroom.databinding.CreateAccountFragmentBinding
import com.example.plantroom.view.base.BaseFragment
import com.example.plantroom.view.sign_in.create_account.data.CreateAccountViewModel
import javax.inject.Inject

class CreateAccountFragment(layout: Int) :
    BaseFragment<CreateAccountFragmentBinding, CreateAccountViewModel>(layout) {

    @Inject
    internal lateinit var factory: ViewModelProviderFactory

    override fun getBindingVariable() = BR.viewModel
    override fun getLayoutId() = R.layout.create_account_fragment

    private lateinit var createAccountViewModel: CreateAccountViewModel
    override fun getViewModel(): CreateAccountViewModel {
        createAccountViewModel =
            ViewModelProviders.of(this, factory).get(CreateAccountViewModel::class.java)

        return createAccountViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}