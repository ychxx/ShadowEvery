package com.yc.everylib.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.yc.everylib.R


/**
 *
 */
object GlideHelper {
    /**
     * 处理图片地址相同，但是两张图的问题
     *
     * @param context
     * @param imageView
     * @param imageUrl
     * @param imageUpdateTime
     */
    fun loadImage(context: Context?, imageView: ImageView, imageUrl: String?, imageUpdateTime: String) {
        if (context == null) return
        if (imageUpdateTime.isEmpty()) {
            Glide.with(context)
                    .asBitmap()
                    .error(R.drawable.every_shape_img_fail)
                    .load(imageUrl)
                    .into(imageView)
        } else {
            Glide.with(context)
                    .applyDefaultRequestOptions(RequestOptions().signature(ObjectKey(imageUpdateTime)))
                    .asBitmap()
                    .error(R.drawable.every_shape_img_fail)
                    .load(imageUrl)
                    .into(imageView)
        }
    }

    fun loadImageCircle(context: Context?, imageView: ImageView, imageUrl: String?, imageUpdateTime: String?) {
        if (context == null) return
        if (imageUpdateTime.isNullOrEmpty()) {
            Glide.with(context)
                    .asBitmap()
                    .error(R.drawable.every_shape_img_fail)
                    .load(imageUrl)
                    .circleCrop()
                    .into(imageView)
        } else {
            Glide.with(context)
                    .applyDefaultRequestOptions(RequestOptions().signature(ObjectKey(imageUpdateTime)))
                    .asBitmap()
                    .error(R.drawable.every_shape_img_fail)
                    .load(imageUrl)
                    .circleCrop()
                    .into(imageView)
        }
    }
}