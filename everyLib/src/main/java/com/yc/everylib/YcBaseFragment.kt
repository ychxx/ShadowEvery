package com.yc.everylib

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch


/**
 * Creator: yc
 * Date: 2021/6/9 19:50
 * UseDes:
 */
abstract class YcBaseFragment<V : ViewBinding>(private val createVB: ((LayoutInflater, ViewGroup?) -> V)? = null) : Fragment() {
    protected var mViewBinding: ViewBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (createVB != null) {
            mViewBinding = createVB.invoke(inflater, container)
            mViewBinding!!.root
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun getContext(): Context {
        return requireContext()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
    }

    protected abstract fun initView(view: View, savedInstanceState: Bundle?)
    override fun onDestroy() {
        super.onDestroy()
        mViewBinding = null
    }

    protected fun <T> LiveData<T>.observe(observer: Observer<T>) {
        this.observe(this@YcBaseFragment, observer)
    }

    protected fun launch(block: suspend () -> Unit) {
        lifecycleScope.launch {
            block()
        }
    }
}