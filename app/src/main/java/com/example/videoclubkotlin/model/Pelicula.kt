package com.example.videoclubkotlin.model

import java.io.Serializable

/*
Autor: Juan Francisco Sánchez González
Fecha: 29/06/2024
Clase: Modelo de datos (Pelicula) con 4 campos y Serializable para trabajar con la información del JSON.
*/

data class Pelicula(
    val id: Int,
    var name: String,
    val description: String,
    val cover: String
) : Serializable
