package com.yc.shadowevery

import android.app.Application
import com.yc.everylib.init.YcEveryInit

/**
 * Creator: yc
 * Date: 2021/7/5 20:41
 * UseDes:
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        YcEveryInit.init(this)
    }
}