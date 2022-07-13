package com.example.organizador.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.organizador.R
import com.example.organizador.databinding.DisciplinaItemBinding
import com.example.organizador.ui.extensions.tentaCarregarImagem
import com.example.organizador.ui.model.Disciplina
import java.text.NumberFormat

class ListaDisciplinasAdapter(

    private val context : Context,
    disciplinas: List<Disciplina>

) : RecyclerView.Adapter<ListaDisciplinasAdapter.ViewHolder>() {

    private val disciplinas = disciplinas.toMutableList()

    class ViewHolder (private val binding: DisciplinaItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun vincula(disciplina: Disciplina) {
             val nome = binding.activityDiscilpinaItemTextViewNome
                nome.text = disciplina.nome

             val descricao = binding.activityDiscilpinaItemTextViewDescricao
                descricao.text = disciplina.descricao

             val horaInicial = binding.activityDiscilpinaItemTextViewHoraInicial
                horaInicial.text= disciplina.hora_inicial

             val horafinal = binding.activityDiscilpinaItemTextViewHoraFinal
                horafinal.text= disciplina.hora_final

           val visibilidade = if (disciplina.imagem != null) {
                View.VISIBLE

            }else{
                View.GONE
            }

            binding.activityDiscilpinaItemImageView.visibility = visibilidade

            binding.activityDiscilpinaItemImageView.tentaCarregarImagem(disciplina.imagem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = DisciplinaItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val disciplina =  disciplinas[position]
        holder.vincula(disciplina)
    }

    override fun getItemCount(): Int = disciplinas.size
    fun atualiza(disciplinas: List<Disciplina>) {
        this.disciplinas.clear()
        this.disciplinas.addAll(disciplinas)
        notifyDataSetChanged()

    }

}
