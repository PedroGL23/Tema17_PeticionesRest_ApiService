package com.example.tema17_peticionesrest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

    object ApiRest {
        lateinit var service: ApiService
        val URL = "https://api.themoviedb.org/3/"
        val URL_IMAGES = "https://image.tmdb.org/t/p/w500/"
        val api_key = "10dec38a69e6242ca6c5dc0072c73ce8"
        val language = "es-ES"
        fun initService() {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofit.create(ApiService::class.java)
        }
    }
