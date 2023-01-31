package com.example.tema17_peticionesrest


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val results: List<Result>,
) {
    data class Result(
        /*
        La anotación SerializedName toma poster_path, que es el nombre que tiene en la api, y
        lo almacena en posterPath que es la variable indicada inmediatamente debajo.
         */
        @SerializedName("poster_path")
        val posterPath: String,
        val title: String,
    )
}