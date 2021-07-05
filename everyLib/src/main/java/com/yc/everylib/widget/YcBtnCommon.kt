package com.yc.everylib.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatButton
import com.yc.everylib.R
import com.yc.everylib.utils.YcResources.getColorRes
import com.yc.everylib.utils.YcUI.dpToPx

/**
 * 通用按钮
 */
class YcBtnCommon @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatButton(context, attrs, defStyleAttr) {
    var mIsBgAble = false //背景颜色能否点击
        set(value) {
            field = value
            onRefreshUI()
        }

    @IdRes
    var mBgResId: Int = 0
        set(value) {
            field = value
            onRefreshUI()
        }

    @IdRes
    var mBgResIdEnable: Int = 0
        set(value) {
            field = value
            onRefreshUI()
        }

    @IdRes
    var mTextColor: Int = 0
        set(value) {
            field = value
            onRefreshUI()
        }

    @IdRes
    var mTextColorEnable: Int = 0
        set(value) {
            field = value
            onRefreshUI()
        }

    init {
        minimumHeight = dpToPx(120f)
        val a = context.obtainStyledAttributes(attrs, R.styleable.YcBtnCommon)
        mBgResId = a.getResourceId(R.styleable.YcBtnCommon_ycBg, R.drawable.every_shape_round_button)
        mBgResIdEnable = a.getResourceId(R.styleable.YcBtnCommon_ycBgEnable, R.drawable.every_shape_round_button_enable)
        mTextColor = a.getColor(R.styleable.YcBtnCommon_ycTextColor, getColorRes(R.color.white))
        mTextColorEnable = a.getColor(R.styleable.YcBtnCommon_ycTextColorEnable, getColorRes(R.color.white))
        a.recycle()
        gravity = Gravity.CENTER
        onRefreshUI()
    }

    fun onRefreshUI() {
        if (mIsBgAble) {
            setTextColor(mTextColor)
            setBackgroundResource(mBgResId)
        } else {
            setTextColor(mTextColorEnable)
            setBackgroundResource(mBgResIdEnable)
        }
    }

}