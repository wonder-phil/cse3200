package com.example.k2025_04_22_met_museum.models

import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer

class ImageContainer(val image: Drawable?, val title: String?) {
    private var myImage = image
    private var myTitle = title

    fun getDrawable() : Drawable? {
        return myImage
    }

    constructor() : this(null, null) { }
}