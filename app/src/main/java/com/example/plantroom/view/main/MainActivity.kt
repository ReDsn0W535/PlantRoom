package com.example.plantroom.view.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.plantroom.BR
import com.example.plantroom.R
import com.example.plantroom.ViewModelProviderFactory
import com.example.plantroom.databinding.ActivityMainBinding
import com.example.plantroom.view.base.BaseActivity
import com.example.plantroom.view.welcome.ui.WelcomeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var fragmentInjector : DispatchingAndroidInjector<Any>
    @Inject
    internal lateinit var factory: ViewModelProviderFactory
    internal lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.main_fragment,
            WelcomeFragment(R.layout.welcome_fragment)
        ).commit()
    }

    override fun getBindingVariable() = BR.viewModel

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        return viewModel
    }

    override fun androidInjector(): AndroidInjector<Any> = fragmentInjector
}