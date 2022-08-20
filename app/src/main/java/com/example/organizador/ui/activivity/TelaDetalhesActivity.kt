package com.example.organizador.ui.activivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.organizador.R
import com.example.organizador.databinding.ActivityTelaDetalhesBinding
import com.example.organizador.ui.extensions.tentaCarregarImagem
import com.example.organizador.ui.model.Disciplina

class TelaDetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTelaDetalhesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarDisciplina()
    }


    private fun tentaCarregarDisciplina() {
        intent.getParcelableExtra<Disciplina>(CHAVE_DISCIPLINA)?.let { disciplinaCarregada ->
            preencheCampos(disciplinaCarregada)
        } ?: finish()
    }

    private fun preencheCampos(disciplinaCarregada: Disciplina) {
        with(binding) {
            activityDetalhesDisciplinaImagem.tentaCarregarImagem(disciplinaCarregada.imagem)
            activityDetalhesDisciplinaNome.text = disciplinaCarregada.nome
            activityDetalhesDisciplinaHoraInicial.text = disciplinaCarregada.hora_inicial
            activityDetalhesDisciplinaHoraFinal.text = disciplinaCarregada.hora_final
            activityDetalhesDisciplinaDescricao.text = disciplinaCarregada.descricao

        }
    }
}
