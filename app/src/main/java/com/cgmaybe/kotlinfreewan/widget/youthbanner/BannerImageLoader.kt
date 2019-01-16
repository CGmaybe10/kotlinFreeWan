package com.cgmaybe.kotlinfreewan.widget.youthbanner

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * youth banner的图片加载器
 */
class BannerImageLoader: ImageLoader() {
    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)
    }
}