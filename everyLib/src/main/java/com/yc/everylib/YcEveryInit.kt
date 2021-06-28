package com.yc.everylib

import android.app.Application
import android.content.res.Resources

/**
 * Creator: yc
 * Date: 2021/6/3 13:54
 * UseDes:
 */
object YcEveryInit {
    lateinit var mApplication: Application

    @JvmStatic
    fun init(app: Application) {
        mApplication = app
    }

    @JvmStatic
    fun getResources(): Resources = mApplication.resources

}