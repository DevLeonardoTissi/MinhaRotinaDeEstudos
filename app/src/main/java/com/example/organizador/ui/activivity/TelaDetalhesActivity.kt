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

        intent.getParcelableExtra<Disciplina>(CHAVE_DISCIPLINA)?.let { disciplinaCarregada ->
            binding.apply {
            activityDetalhesProdutoImagem.tentaCarregarImagem(disciplinaCarregada.imagem)
            activityDetalhesProdutoNome.text = disciplinaCarregada.nome
            activityDetalhesProdutoDescricao.text = disciplinaCarregada.descricao

        }}
    }
}
