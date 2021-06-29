package com.yc.everylib.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import com.yc.everylib.YcEveryInit

/**
 * 用于获取资源(需初始化YcUtilsInit.init(x))
 */
object YcResources {

    /**
     * 获取颜色值
     *
     * @param resId 颜色资源id
     * @return 颜色值
     */
    @JvmStatic
    @ColorInt
    fun getColor(resId: Int, resources: Resources = YcEveryInit.getResources()): Int {
        return resources.getColor(resId)
    }

    /**
     * 获取颜色值
     *
     * @param color 十六进制
     * @return
     */
    @JvmStatic
    @ColorInt
    fun getColor(color: String?): Int {
        return Color.parseColor(color)
    }

    /**
     * 获取颜色值
     *
     * @param resId 颜色资源id
     * @return 颜色值
     */
    @JvmStatic
    @ColorInt
    fun getColor(resId: Int): Int {
        return getColor(resId)
    }

    /**
     * 获取Drawable
     *
     * @param resId Drawable资源id
     * @return Drawable
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    @JvmStatic
    fun getDrawable(resId: Int, resources: Resources = YcEveryInit.getResources()): Drawable {
        return resources.getDrawable(resId)
    }

    /**
     * 获取Drawable
     *
     * @param resId Drawable资源id
     * @return Drawable
     */
    @JvmStatic
    fun getDrawable(resId: Int): Drawable? {
        return getDrawable(resId)
    }

    /**
     * 获取字符串
     *
     * @param resId 字符串资源id
     * @return 字符串
     */
    @JvmStatic
    fun getString(resId: Int, resources: Resources = YcEveryInit.getResources()): String {
        return resources.getString(resId)
    }

    /**
     * 获取字符串数组
     *
     * @param resId 数组资源id
     * @return 字符串数组
     */
    @JvmStatic
    fun getStringArray(resId: Int, resources: Resources = YcEveryInit.getResources()): Array<String> {
        return resources.getStringArray(resId)
    }

    /**
     * 获取字符串数组
     *
     * @param resId 数组资源id
     * @return 字符串数组
     */
    @JvmStatic
    fun getStringList(resId: Int, resources: Resources = YcEveryInit.getResources()): List<String> {
        return mutableListOf(*resources.getStringArray(resId))
    }

}