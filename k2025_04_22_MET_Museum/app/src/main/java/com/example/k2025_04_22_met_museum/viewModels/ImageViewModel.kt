package com.example.k2025_04_22_met_museum.viewModels

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.k2025_04_22_met_museum.models.ImageContainer
import com.example.k2025_04_22_met_museum.models.OneMetApiCall
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import kotlin.coroutines.CoroutineContext
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class ImageViewModel : ViewModel() {

    private var oneImage: ImageContainer? = null


    private lateinit var getOneMetApiCall: OneMetApiCall


    suspend fun getImageDrawable(imageUrl: String, context: CoroutineContext): Drawable? {
        val retrofit = Retrofit.Builder()
            .baseUrl(imageUrl) // Placeholder; not used because we use @Url
            .build()

        val service = retrofit.create(OneMetApiCall::class.java)

        val response = service.downloadImage(imageUrl)
        if (response.isSuccessful) {
            response.body()?.byteStream()?.use { inputStream ->
                return Drawable.createFromStream(inputStream, "src")
            }
        }
        return null
    }

    fun getOneImage() : ImageContainer? {
        var myImage: ImageContainer? = null
        viewModelScope.launch {

            val drawable = getImageDrawable("https://images.metmuseum.org/CRDImages/ep/web-large/DT1567.jpg", coroutineContext)
           myImage = ImageContainer(drawable!!,"good")
        }
        return myImage
    }

}