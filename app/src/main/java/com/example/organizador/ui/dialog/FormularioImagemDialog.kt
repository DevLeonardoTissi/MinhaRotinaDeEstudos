package com.example.organizador.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.organizador.databinding.DialogFormularioImagemBinding
import com.example.organizador.ui.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {

        DialogFormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {

                urlPadrao?.let {
                    dialogFormularioImagemImageView.tentaCarregarImagem(it)
                    dialogFormularioImagemEditTextUrl.setText(it)
                }
                dialogFormularioImagemBotaoCarregar.setOnClickListener {
                    val url = dialogFormularioImagemEditTextUrl.text.toString()
                    dialogFormularioImagemImageView.tentaCarregarImagem(url)
                }

                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = dialogFormularioImagemEditTextUrl.text.toString()
                        quandoImagemCarregada(url)

                    }
                    .setNegativeButton("Cancelar") { _, _ ->

                    }
                    .show()

            }
    }


}


