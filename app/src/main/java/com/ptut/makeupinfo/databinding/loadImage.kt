package com.ptut.makeupinfo.databinding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ptut.makeupinfo.R
import com.ptut.makeupinfo.glide.GlideImageModel
import com.ptut.makeupinfo.glide.GlideImageRequestListener

@BindingAdapter("android:src")
fun loadImage(view: ImageView, imageUrl: String) {
  loadImageFast(view,imageUrl)
}

private fun loadImageFast(view:ImageView,fullImageUrl: String) {
  val requestOption = RequestOptions()
    .placeholder(R.drawable.placeholder).centerCrop()

  Glide.with(view.context).load(GlideImageModel(fullImageUrl))
    .transition(DrawableTransitionOptions.withCrossFade())
    .thumbnail(Glide.with(view.context)
      .load(GlideImageModel("https://cdnimg.webstaurantstore.com/images/products/large/497201/1873082.jpg"))
      .apply(requestOption))
    .apply(requestOption)
    .into(view)
}