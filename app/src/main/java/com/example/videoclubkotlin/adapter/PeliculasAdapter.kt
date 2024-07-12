package com.example.videoclubkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoclubkotlin.R
import com.example.videoclubkotlin.databinding.ItemPeliculaBinding
import com.example.videoclubkotlin.model.Pelicula
import com.example.videoclubkotlin.utils.inflate
import java.util.Locale

/*
Autor: Juan Francisco Sánchez González
Fecha: 29/06/2024
Clase: Adaptador personalizado con las diferentes funcionalidades que se pueden hacer sobre la lista.
*/

class PeliculasAdapter(private val listener: (Pelicula) -> Unit): RecyclerView.Adapter<PeliculasViewHolder>() {

    private val peliculasList = arrayListOf<Pelicula>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val itemBinding = ItemPeliculaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeliculasViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = peliculasList.size

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        val peli = peliculasList[position]
        holder.bind(peli)
        holder.itemView.setOnClickListener{ listener(peli) }
    }

    fun refrescarList(pelList: ArrayList<Pelicula>) {
        peliculasList.addAll(pelList)
        notifyDataSetChanged()
    }

    fun filterPorNombre(pelis: List<Pelicula>) {
        peliculasList.clear()
        peliculasList.addAll(pelis)
        notifyDataSetChanged()
    }

    fun orderByName() {
        val sortedList = peliculasList.sortedBy { it.name }
        peliculasList.clear()
        peliculasList.addAll(sortedList)
        notifyDataSetChanged()
    }

    fun insertPelicula() {
        val nuevaPelicula = Pelicula(13, "Le llaman Bodhi", "Prueba descripción", "https://pics.filmaffinity.com/point_break-935042152-mmed.jpg")
        peliculasList.add(0, nuevaPelicula)
        notifyItemInserted(0)
    }

    fun modifyPelicula() {
        peliculasList[0].name = "Prueba Mod"
        notifyItemChanged(0)
    }

    fun deletePelicula() {
        peliculasList.removeAt(0)
        notifyItemRemoved(0)
    }
}