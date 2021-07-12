package com.yc.everylib.recycleView

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.yc.everylib.base.YcBaseDataSource
import com.yc.everylib.data.entity.YcDataSourceEntity

/**
 * Creator: yc
 * Date: 2021/7/6 15:28
 * UseDes:pager创建的封装
 */
object YcPager {
    @JvmStatic
    inline fun <T : Any> getPager(
        pageSize: Int = 30,
        initialLoadSize: Int = 30,
        crossinline block: suspend (pageIndex: Int, pageSize: Int) -> YcDataSourceEntity<T>
    ): Pager<Int, T> {
        return Pager(PagingConfig(pageSize = pageSize, initialLoadSize = initialLoadSize), pagingSourceFactory = {
            return@Pager object : YcBaseDataSource<T>() {
                override suspend fun load(pageIndex: Int, pageSize: Int): YcDataSourceEntity<T> {
                    return block(pageIndex, pageSize)
                }
            }
        })
    }
}