package com.example.videoclubkotlin.utils

import android.content.Context
import android.service.media.MediaBrowserService.BrowserRoot
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.io.InputStream
import java.nio.charset.Charset

/*
Autor: Juan Francisco Sánchez González
Fecha: 29/06/2024
Clase: Extensiones para 2 objetos (Context y ViewGroup), se utilizan para agregar o extender funcionalidades
o métodos a esos objetos.
*/

fun Context.getJsonFromAssets(file: String): String? {
    var json: String? = null
    try {
        val stream: InputStream = assets.open(file)
        val size: Int = stream.available()
        val buffer = ByteArray(size)
        stream.read(buffer)
        stream.close()
        json = String(buffer, Charset.defaultCharset())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return json
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attatchToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attatchToRoot)
