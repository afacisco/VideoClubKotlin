package com.example.videoclubkotlin.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import com.example.videoclubkotlin.R
import com.example.videoclubkotlin.adapter.PeliculasAdapter
import com.example.videoclubkotlin.databinding.ActivityMainBinding
import com.example.videoclubkotlin.model.Pelicula
import com.example.videoclubkotlin.utils.getJsonFromAssets
import com.google.gson.Gson
import java.util.Locale

/*
Autor: Juan Francisco Sánchez González
Fecha: 29/06/2024
Clase: Actividad principal que utiliza View-Binding y un fichero JSON como asset para recoger la información.
Se trata de un listado (RecyclerView y CardView) de películas, con su detalle y diferentes funcionalidades
para operar con el listado (buscar, ordenar, eliminar, insertar y borrar).
*/

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PeliculasAdapter
    private val copyListPelicula = arrayListOf<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Preaprar el uso de View-Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = PeliculasAdapter(::onPeliculaClick)
        binding.recicladorListado.adapter = adapter

        adapter.refrescarList(getListFromJson())

        binding.buscadorCampo.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val filterList = copyListPelicula.filter { pel ->
                        pel.name.toLowerCase(Locale.getDefault()).contains(newText)
                    }
                    adapter.filterPorNombre(filterList)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

        })

        binding.btnSort.setOnClickListener() {
            adapter.orderByName()
        }

        binding.btnInsert.setOnClickListener() {
            adapter.insertPelicula()
        }

        binding.btnMod.setOnClickListener() {
            adapter.modifyPelicula()
        }

        binding.btnDelete.setOnClickListener() {
            adapter.deletePelicula()
        }
    }

    private fun getListFromJson(): ArrayList<Pelicula> {
        val json = getJsonFromAssets("movies.json")
        var peliculaList = arrayListOf<Pelicula>()
        json?.let {
            peliculaList = ArrayList(Gson().fromJson(json, Array<Pelicula>::class.java).toList())
            copyListPelicula.addAll(peliculaList)
        }
        return ArrayList(peliculaList)
    }

    private fun onPeliculaClick(peli: Pelicula) {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("pelicula", peli)
        startActivity(intent)
    }
}