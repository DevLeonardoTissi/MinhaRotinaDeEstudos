package com.example.organizador.ui.extensions

import android.widget.ImageView
import coil.load
import com.example.organizador.R

fun ImageView.tentaCarregarImagem(url : String? = null){
    load(url){
        fallback(com.example.organizador.R.drawable.erro)
        error(com.example.organizador.R.drawable.erro)
        placeholder(com.example.organizador.R.drawable.placeholder)

    }
}