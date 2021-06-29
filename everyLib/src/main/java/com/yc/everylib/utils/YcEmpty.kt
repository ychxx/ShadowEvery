package com.yc.everylib.utils

import android.text.TextUtils

/**
 * SimpleDes:
 * Creator: Sindi
 * Date: 2021-06-29
 * UseDes:
 */


/**
 * 获取字符串
 *
 * @param data        数据
 * @param dataIfEmpty null时返回的字符串
 * @return
 */
fun getNoEmpty(data: String?, dataIfEmpty: String?): String? {
    return if (TextUtils.isEmpty(data)) dataIfEmpty else data
}