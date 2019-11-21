package com.example.plantroom.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.example.plantroom.R
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(), HasAndroidInjector{
    private var fragmentManager : FragmentManager = supportFragmentManager
    private lateinit var dataBinding : T
    private lateinit var viewModel : V
    open fun getViewDataBinding() = dataBinding
    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewModel = getViewModel()
        dataBinding.setVariable(getBindingVariable(), viewModel)
        dataBinding.executePendingBindings()
    }

    open fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    public fun addFragment(fragment: BaseFragment<*,*>, tag : String) = fragmentManager
        .beginTransaction().add(R.id.main_fragment, fragment, tag).commit()
    public fun replaceFragment(fragment: BaseFragment<*,*>, tag : String) = fragmentManager
        .beginTransaction().replace(R.id.main_fragment, fragment, tag).commit()
    public fun removeFragment(fragment: BaseFragment<*,*>) = fragmentManager.beginTransaction().remove(fragment).commit()
}