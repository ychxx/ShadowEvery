package com.yc.shadowevery

import android.os.Bundle
import android.view.View
import com.yc.everylib.base.YcBaseFragment
import com.yc.everylib.base.YcBaseViewModel
import com.yc.everylib.recycleView.YcViewHolder
import com.yc.shadowevery.databinding.TestEdtimgActivityBinding

/**
 * Creator: yc
 * Date: 2021/6/28 10:24
 * UseDes:
 */
class TestFragment : YcBaseFragment<TestEdtimgActivityBinding>(TestEdtimgActivityBinding::inflate) {
    val viewModel: YcBaseViewModel by ycViewModels()
    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

}