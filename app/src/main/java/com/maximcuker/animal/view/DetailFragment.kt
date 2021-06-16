package com.maximcuker.animal.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.maximcuker.animal.R
import com.maximcuker.animal.databinding.FragmentDetailBinding
import com.maximcuker.animal.model.Animal
import com.maximcuker.animal.model.AnimalPalette
import com.maximcuker.animal.util.getProgressDrawable
import com.maximcuker.animal.util.loadImage

class DetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailBinding
    var animal: Animal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }

        animal?.imageUrl?.let {
            setupBackgroundColor(it)
        }

        dataBinding.animal = animal
    }

    private fun setupBackgroundColor(imageUrl: String) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object: CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate() { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                            dataBinding.palette = AnimalPalette(intColor)
                        }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
    }
}