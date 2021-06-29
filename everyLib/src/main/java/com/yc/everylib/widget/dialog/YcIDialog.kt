package com.yc.everylib.widget.dialog

/**
 * Creator: yc
 * Date: 2021/6/28 11:39
 * UseDes:
 */
interface YcIDialog {
    fun setMsg(msg: String)
    fun setLeftBtnText(leftBtnText: String)
    fun setOnLeftClick(onClick: YcOnClick)
    fun setRightBtnText(rightBtnText: String)
    fun setOnRightClick(onClick: YcOnClick)
    fun show()
}