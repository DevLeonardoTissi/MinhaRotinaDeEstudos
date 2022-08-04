package com.example.organizador.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
 data  class Disciplina (
      val nome: String,
      val descricao: String,
      val hora_inicial: String,
      val hora_final: String? = null,
      val imagem :String? = null
          ) : Parcelable