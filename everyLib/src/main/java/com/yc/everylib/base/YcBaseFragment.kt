package com.yc.everylib.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.yc.everylib.utils.YcViewModelLazy
import kotlinx.coroutines.launch


/**
 * Creator: yc
 * Date: 2021/6/9 19:50
 * UseDes:
 */
abstract class YcBaseFragment<VB : ViewBinding>(private val createVB: ((LayoutInflater, ViewGroup?, Boolean) -> VB)? = null) : Fragment() {
    protected var mViewBinding: VB? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (createVB != null) {
            mViewBinding = createVB.invoke(inflater, container, false)
            mViewBinding!!.root
        } else {
            super.onCreateView(inflater, container, savedInstanceState)
        }
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

    @MainThread
    protected inline fun <reified VM : YcBaseViewModel> Fragment.ycViewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }
        return YcViewModelLazy(VM::class, { viewModelStore }, factoryPromise, {
            it.mIsShowLoading.observe(this@YcBaseFragment) {
                if (it) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        })
    }

    @MainThread
    protected inline fun <reified VM : YcBaseViewModel> Fragment.ycViewModelsActivity(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }
        return YcViewModelLazy(VM::class, { viewModelStore }, factoryPromise, {
            it.mIsShowLoading.observe(requireActivity()) {
                if (it) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        })
    }

    protected fun showLoading() {}
    protected fun hideLoading() {}
    protected fun launch(block: suspend () -> Unit) {
        lifecycleScope.launch {
            block()
        }
    }
}