package io.parrotsoftware.qatest.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import io.parrotsoftware.qatest.R

@BindingAdapter("app:image_url")
fun loadImage(view: ImageView, logoUrl: String) {
    if (logoUrl.isEmpty()) {
        view.setImageResource(R.drawable.ic_place_holder)
    } else {
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide.with(view.context)
            .load(logoUrl)
            .centerCrop()
            .transition(withCrossFade(factory))
            .placeholder(R.drawable.ic_place_holder)
            .into(view)
    }
}

@BindingAdapter("app:price")
fun setPrice(view: TextView, price: Float) {
    view.text = price.toString()
}
