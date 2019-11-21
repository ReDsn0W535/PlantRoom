package com.example.plantroom.view.sign_in.sign_in.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import com.example.plantroom.R
import com.example.plantroom.ViewModelProviderFactory
import com.example.plantroom.databinding.SignInFragmentBinding
import com.example.plantroom.view.base.BaseFragment
import com.example.plantroom.view.sign_in.sign_in.data.SignInViewModel
import javax.inject.Inject

class SignInFragment(layout: Int) : BaseFragment<SignInFragmentBinding, SignInViewModel>(layout) {
    @Inject
    internal lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: SignInViewModel
    override fun getBindingVariable() = BR.viewModel
    override fun getLayoutId() = R.layout.sign_in_fragment

    override fun getViewModel(): SignInViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(SignInViewModel::class.java)
        return viewModel
    }

}
