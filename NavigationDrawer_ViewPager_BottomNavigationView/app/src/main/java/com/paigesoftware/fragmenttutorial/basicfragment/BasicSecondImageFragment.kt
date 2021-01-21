package com.paigesoftware.fragmenttutorial.basicfragment

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.paigesoftware.fragmenttutorial.R
import kotlinx.android.synthetic.main.fragment_basic_second_image.*


class BasicSecondImageFragment: Fragment() {

    private val imageURL = "https://i.pinimg.com/originals/56/b2/ba/56b2badb829d74bc75b26532cf2c5194.jpg"

    companion object {
        fun newInstance() = BasicSecondImageFragment()
    }

    private fun loadImageUsingGlide() {
        progressbar_basicsecondimagefragment.visibility = View.VISIBLE
        Glide.with(this)
            .asBitmap()
            .load(imageURL)
            .into(object : BitmapImageViewTarget(imageview_basicsecondimagefragment){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                    progressbar_basicsecondimagefragment.visibility = View.GONE
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadImageUsingGlide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_second_image, container, false)
    }

}