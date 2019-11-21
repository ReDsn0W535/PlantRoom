package com.example.plantroom.view.welcome.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.plantroom.BR
import com.example.plantroom.R
import com.example.plantroom.ViewModelProviderFactory
import com.example.plantroom.databinding.WelcomeFragmentBinding
import com.example.plantroom.view.base.BaseFragment
import com.example.plantroom.view.sign_in.create_account.ui.CreateAccountFragment
import com.example.plantroom.view.welcome.data.WelcomeViewModel
import com.example.plantroom.view.welcome.navigator.WelcomeNavigator
import com.example.plantroom.view.sign_in.sign_in.ui.SignInFragment
import javax.inject.Inject

class WelcomeFragment(layout : Int) : BaseFragment<WelcomeFragmentBinding, WelcomeViewModel>(layout),
    WelcomeNavigator {
    @Inject
    internal lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: WelcomeViewModel

    override fun getLayoutId() = R.layout.welcome_fragment
    override fun getBindingVariable() = BR.viewModel

    override fun getViewModel(): WelcomeViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(WelcomeViewModel::class.java)
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onStart() {
        super.onStart()
    }



    override fun createAccount() {
        getBaseActivity().replaceFragment(CreateAccountFragment(R.layout.create_account_fragment), "CreateAccountFragment")
    }

    override fun login() {
        getBaseActivity().replaceFragment(SignInFragment(R.layout.create_account_fragment), "SignInFragment")
    }

    override fun isLogined() {
        TODO("переход к следующему блоку") //To change body of created functions use File | Settings | File Templates.
    }

}