package com.example.organizador.ui.dao

import com.example.organizador.ui.model.Disciplina

class DisciplinasDao {

    //alt+ent e adicionar companion object

    fun adiciona(disciplina: Disciplina){
        Companion.disciplinas.add(disciplina)

    }

    fun buscatodos() : List<Disciplina>{
        return Companion.disciplinas.toList()
    }

    companion object {
        private val disciplinas = mutableListOf<Disciplina>()
    }


}