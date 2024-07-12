package com.example.videoclubkotlin.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.videoclubkotlin.databinding.ItemPeliculaBinding
import com.example.videoclubkotlin.model.Pelicula
import com.squareup.picasso.Picasso

/*
Autor: Juan Francisco Sánchez González
Fecha: 29/06/2024
Clase: ViewHolder para el bind de los datos en la View
*/

class PeliculasViewHolder(private val itemBinding: ItemPeliculaBinding): RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(pelicula: Pelicula) {
        itemBinding.tituloPelicula.text = pelicula.name
        pelicula.cover?.let {
            Picasso.get().load(pelicula.cover).into(itemBinding.imagenPelicula);
        }
    }
}