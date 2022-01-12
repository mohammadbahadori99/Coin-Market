package com.mohammad.bahadori.features

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        configCoilImageLoader()
    }

    private fun configCoilImageLoader() {
        // config Coil library
        val imageLoader =
            ImageLoader.Builder(this)
                .componentRegistry {
                    add(SvgDecoder(this@MyApp))
                }
                .build()

        Coil.setImageLoader(imageLoader)

    }
}