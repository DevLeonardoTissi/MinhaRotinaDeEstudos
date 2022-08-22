package com.example.organizador.ui.extensions

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.example.organizador.ui.activivity.FormularioActivity

fun Context.trocaTelaFormulario(context: Context){
    val intentTrocaTela = Intent(context, FormularioActivity::class.java)
    startActivity(intentTrocaTela)
}


