package com.ptut.makeupinfo.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("android:src")
fun loadImage(view: ImageView, imageUrl: String) {
  Glide.with(view)
    .load(imageUrl)
    .into(view)
}