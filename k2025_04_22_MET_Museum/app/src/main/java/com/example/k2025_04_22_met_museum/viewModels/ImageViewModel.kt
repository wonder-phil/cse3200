package com.example.k2025_04_22_met_museum.viewModels

import android.graphics.drawable.DrawableContainer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k2025_04_22_met_museum.models.ImageContainer
import com.example.k2025_04_22_met_museum.models.OneMetApiCall
import retrofit2.Retrofit

class ImageViewModel : ViewModel() {

    private var oneImage: ImageContainer? = null

    private lateinit var getOneMetApiCall: OneMetApiCall

    init {
        val retrofitInit = Retrofit.Builder()
            //.addConverterFactory()
            .baseUrl("https://images.metmuseum.org/CRDImages/ep/web-large/")
            .build()

        getOneMetApiCall = retrofitInit.create(OneMetApiCall::class.java)
    }

}