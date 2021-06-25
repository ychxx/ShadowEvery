package com.yc.everylib.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import com.yc.everylib.utils.YcViewModelLazy
import com.yc.everylib.manager.YcActivityManager
import kotlinx.coroutines.launch

/**
 * Creator: yc
 * Date: 2021/6/9 16:47
 * UseDes:
 */
@SuppressLint("SetTextI18n")
abstract class YcBaseActivity<VB : ViewBinding>(private val createVB: ((LayoutInflater) -> VB)? = null) : AppCompatActivity() {
    protected lateinit var mViewBinding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (createVB != null) {
            mViewBinding = createVB.invoke(LayoutInflater.from(this as Context))
            setContentView(mViewBinding.root)
            initView()
        }
        YcActivityManager.addActivity(this)
    }

    override fun onDestroy() {
        YcActivityManager.finishActivity(this)
        super.onDestroy()
    }

    fun getContext(): Context {
        return this as Context
    }

    abstract fun initView()

    @MainThread
    protected inline fun <reified VM : YcBaseViewModel> ComponentActivity.ycViewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }
        return YcViewModelLazy(VM::class, { viewModelStore }, factoryPromise, {
            it.mIsShowLoading.observe(this@YcBaseActivity) {
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
    protected fun launch(block: suspend () -> Unit) =
        lifecycleScope.launch {
            block()
        }

    protected fun <T> LiveData<T>.observe(observer: Observer<T>) {
        this.observe(this@YcBaseActivity, observer)
    }

    protected inline fun startResultYc(intent: Intent, crossinline success: ((result: ActivityResult) -> Unit)) {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode != Activity.RESULT_OK) {
                success(it)
            }
        }.launch(intent)
    }

    fun show(msg: String?) {
        if (this.isFinishing) {
            return
        }
        if (msg == null) {
            Toast.makeText(this, "-", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
