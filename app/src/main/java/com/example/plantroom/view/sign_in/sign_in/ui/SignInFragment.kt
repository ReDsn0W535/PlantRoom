package com.example.plantroom.view.sign_in.sign_in.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import com.example.plantroom.R
import com.example.plantroom.ViewModelProviderFactory
import com.example.plantroom.databinding.SignInFragmentBinding
import com.example.plantroom.view.base.BaseFragment
import com.example.plantroom.view.sign_in.sign_in.data.SignInViewModel
import com.example.plantroom.view.sign_in.sign_in.navigator.SignInNavigator
import javax.inject.Inject


class SignInFragment(layout: Int) : BaseFragment<SignInFragmentBinding, SignInViewModel>(layout),
    SignInNavigator {
    @Inject
    internal lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: SignInViewModel
    override fun getBindingVariable() = BR.viewModel
    override fun getLayoutId() = R.layout.sign_in_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding()?.googleSignIn?.setOnClickListener {
            startActivityForResult(viewModel.getSignInClient(), 0)
        }

    }

    override fun getViewModel(): SignInViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(SignInViewModel::class.java)
        return viewModel
    }

    override fun goToMainMarket() {
        Toast.makeText(context, "goToMainMarket", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0)
            viewModel.loginWithGoogle(data!!)
    }
}
