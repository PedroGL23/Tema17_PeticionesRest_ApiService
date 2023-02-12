package com.example.tema17_peticionesrest

import java.io.Serializable

data class GenresResponse(
    val genres: List<Genre>
): Serializable {
    data class Genre(
        val id: Int,
        val name: String
    ): Serializable
}