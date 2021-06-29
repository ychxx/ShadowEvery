package com.yc.everylib.widget.dialog

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.yc.everylib.R

/**
 * 通用的对话框
 */
class YcCommonDialog @JvmOverloads constructor(
    context: Context,
    mLifecycleOwner: LifecycleOwner,
    theme: Int = R.style.YcCommonDialogStyle
) : Dialog(context, theme) {
    private var mContentView: TextView//内容
    private var mLeftBtn: Button
    private var mRightBtn: Button
    private var mLineV: View
    private var mOnLeftClick: YcOnClick? = null
    private var mOnRightClick: YcOnClick? = null

    init {
        mLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                Log.e("YcCommonDialog", "onDestroy: ")
                if (this@YcCommonDialog.isShowing) {
                    cancel()
                }
            }
        })
        setContentView(R.layout.yc_widget_dialog)
        mContentView = findViewById<View>(R.id.dialogContentTv) as TextView
        mLeftBtn = findViewById<View>(R.id.dialogLeftBt) as Button
        mLineV = findViewById(R.id.dialogLineV)
        mRightBtn = findViewById<View>(R.id.dialogRightBt) as Button

        //设置对话框位置大小
        val dialogWindow = window
        dialogWindow!!.setGravity(Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL)
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        val lp = dialogWindow.attributes
        dialogWindow.attributes = lp //此处暂未设置偏移量
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        mLeftBtn.setOnClickListener { v: View? ->
            dismiss()
            if (mOnLeftClick != null) {
                mOnLeftClick?.onClick()
            }
        }
        mRightBtn.setOnClickListener { v: View? ->
            dismiss()
            mOnRightClick?.onClick()
        }
    }

    fun setSingleBtnText(text: String?): YcCommonDialog {
        mLeftBtn.text = text ?: ""
        return this
    }

    /**
     * 设置右侧按钮点击事件
     */
    fun setSingleOnClick(onLeftClick: YcOnClick?): YcCommonDialog {
        mOnLeftClick = onLeftClick
        return this
    }

    fun showSingle() {
        mRightBtn.visibility = View.GONE
        mLineV.visibility = View.GONE
        show()
    }

    /**
     * 设置提示语
     */
    fun setMsg(msg: String?): YcCommonDialog {
        mContentView.text = msg ?: "暂无内容"
        return this
    }

    /**
     * 设置左侧按钮字符串
     */
    fun setLeftBtnText(text: String?): YcCommonDialog {
        mLeftBtn.text = text ?: ""
        return this
    }

    /**
     * 设置左侧按钮点击事件
     */
    fun setOnLeftClick(onLeftClick: YcOnClick?): YcCommonDialog {
        mOnLeftClick = onLeftClick
        return this
    }

    /**
     * 设置右侧按钮字符串
     */
    fun setRightBtnText(text: String?): YcCommonDialog {
        mRightBtn.text = text ?: ""
        return this
    }

    /**
     * 设置右侧按钮点击事件
     */
    fun setOnRightClick(onRightClick: YcOnClick?): YcCommonDialog {
        mOnRightClick = onRightClick
        return this
    }
}