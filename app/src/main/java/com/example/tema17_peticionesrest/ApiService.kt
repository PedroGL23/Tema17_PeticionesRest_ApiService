package com.example.tema17_peticionesrest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("genre/movie/list")
    fun getGenres(
        /*
        Estos serían valores por defecto, que no varían en ninguna petición a la api.
        La de language debería de estar vacía, pues sí podría modificarse.
         */
        @Query("api_key") apikey: String = ApiRest.api_key,
        @Query("language") language: String = ApiRest.language
    ): Call<GenresResponse>


    @GET ("discover/movie")
    fun getMovies(
        @Query("with_genres")with_genres: String,
        @Query("api_key") apikey: String = ApiRest.api_key,
        @Query("language") language: String = ApiRest.language
    ): Call<MoviesGenresResponse>

}



