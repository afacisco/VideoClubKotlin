package com.example.videoclubkotlin.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videoclubkotlin.R
import com.example.videoclubkotlin.databinding.ActivityDetalleBinding
import com.example.videoclubkotlin.databinding.ActivityMainBinding
import com.example.videoclubkotlin.model.Pelicula
import com.squareup.picasso.Picasso

/*
Autor: Juan Francisco Sánchez González
Fecha: 29/06/2024
Clase: Actividad del detalle de cada película, utiliza View-Binding.
*/

class DetalleActivity : AppCompatActivity() {

    private var peli: Pelicula? = null
    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Preaprar el uso de View-Binding
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recogerPelicula()
        renderUi()
    }

    private fun recogerPelicula() {
        peli = intent.getSerializableExtra("pelicula") as Pelicula?
    }

    private fun renderUi() {
        binding.detalleTitulo.text = peli?.name
        binding.detalleDescripcion.text = peli?.description
        peli?.cover?.let { mCover ->
            Picasso.get().load(mCover).into(binding.detalleImagen);
        }
    }
}