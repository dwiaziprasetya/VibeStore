package com.example.vibestore.model

import com.example.vibestore.R

data class SliderImage(
    val image: Int
)

val dummySliderImage = listOf(
    SliderImage(R.drawable.sliderimageone),
    SliderImage(R.drawable.sliderimagetwo),
    SliderImage(R.drawable.sliderimagethree),
    SliderImage(R.drawable.sliderimagefour),
)
