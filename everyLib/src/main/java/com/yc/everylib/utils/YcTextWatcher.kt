package com.yc.everylib.utils

import android.text.Editable
import android.text.TextWatcher

/**
 * SimpleDes:
 * Creator: Sindi
 * Date: 2021-07-06
 * UseDes:
 */
open class YcTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable?) {}
}
