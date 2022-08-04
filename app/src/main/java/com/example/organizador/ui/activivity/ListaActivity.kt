package com.example.organizador.ui.activivity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.organizador.R
import com.example.organizador.databinding.ActivityListaBinding
import com.example.organizador.ui.dao.DisciplinasDao
import com.example.organizador.ui.recyclerview.adapter.ListaDisciplinasAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaActivity : AppCompatActivity() {

    private var teste: Boolean = true
    private val dao = DisciplinasDao()
    private val adapter = ListaDisciplinasAdapter(this, disciplinas = dao.buscatodos())
    private val binding by lazy {
        ActivityListaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configuraRecyclerView()
        configuraBotaoTrocaTela()

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscatodos())

    }

    private fun configuraBotaoTrocaTela() {
        val botaotrocatela = binding.activityListaFloatingActionButton
        botaotrocatela.setOnClickListener {
            val intent_troca_tela = Intent(this, FormularioActivity::class.java)
            startActivity(intent_troca_tela)
        }
    }

    private fun configuraRecyclerView() {
        val recyclerview = binding.activityListaRecyclerView

        recyclerview.adapter = adapter

        //layout grid pede o contexto, e o numero de colunas
        recyclerview.layoutManager = LinearLayoutManager(this)

        val botaotrocalayout = binding.activityListaButtonAlteralayout
        botaotrocalayout.setOnClickListener {
            if (teste == false) {
                recyclerview.layoutManager = LinearLayoutManager(this)
                botaotrocalayout.setImageResource(R.drawable.ic_grid)
                teste = true
            } else {
                recyclerview.layoutManager = GridLayoutManager(this, 2)
                botaotrocalayout.setImageResource(R.drawable.ic_list)
                teste = false
            }


        }
        adapter.quandoClicaNoItem = { disciplina ->
            val intent = Intent(
                this, TelaDetalhesActivity::class.java
            ).apply {
                putExtra(CHAVE_DISCIPLINA, disciplina)

            }
            startActivity(intent)

        }
    }
}

