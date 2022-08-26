package com.example.organizador.ui.model

import android.graphics.Bitmap

class Usuario(
) {

    var nome: String? = null
        private set
    var foto: Bitmap? = null
        private set

    fun alteraFoto(foto: Bitmap) {
        this.foto = foto
    }

    fun alteraNome(nome: String){
        this.nome = nome
    }
}

