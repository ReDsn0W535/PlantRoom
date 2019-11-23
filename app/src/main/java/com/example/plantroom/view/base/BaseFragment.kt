package com.example.plantroom.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.plantroom.view.base.uiLifecycleScope.UiLifecycleScope
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>>(layout: Int) :
    Fragment(layout) {
    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null
    private lateinit var rootActivity: BaseActivity<*, *>
    private var mRootView: View? = null
    private val uiScope = UiLifecycleScope()

    open fun getViewDataBinding() = mViewDataBinding
    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V



    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        lifecycle.addObserver(mViewModel as V)
        lifecycle.addObserver(uiScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding?.apply {
            setVariable(getBindingVariable(), mViewModel)
            lifecycleOwner = this@BaseFragment
            executePendingBindings()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.let { mRootView = it?.root }
        return mRootView
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rootActivity = context as BaseActivity<*, *>
    }

    protected fun getBaseActivity() = rootActivity

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel as V)
    }
}