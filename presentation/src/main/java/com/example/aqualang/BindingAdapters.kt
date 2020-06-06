package com.example.aqualang

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("pictureUrl")
fun bindPicture(imgView: ImageView, pictureUrl: String?){

    if(pictureUrl == null){
        imgView.setImageResource(R.drawable.aqualang_logo)
    }
    else{
        Glide
            .with(imgView.context)
            .load(pictureUrl)
            .into(imgView)
    }
}