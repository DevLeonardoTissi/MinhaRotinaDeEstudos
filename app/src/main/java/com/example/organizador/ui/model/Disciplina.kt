package com.example.organizador.ui.model

 data  class Disciplina (
      val nome: String,
      val descricao: String,
      val hora_inicial: String,
      val hora_final: String? = null,
      val imagem :String? = null
          ) {
}