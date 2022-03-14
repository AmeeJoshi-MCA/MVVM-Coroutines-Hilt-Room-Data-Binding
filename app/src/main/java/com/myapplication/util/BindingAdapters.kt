package com.myapplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.myapplication.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.load(url)
    }
}

@BindingAdapter("app:pictureLike")
fun setTimelineLikeImage(image: ImageView, imageStatus: Boolean) {
    if (imageStatus) {
        image.setImageResource(R.drawable.ic_fav_fill)
    } else {
        image.setImageResource(R.drawable.ic_fav_outline)
    }
}
