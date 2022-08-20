package com.example.organizador.ui.activivity

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.organizador.R
import com.example.organizador.databinding.ActivityFormularioBinding
import com.example.organizador.databinding.DialogFormularioImagemBinding
import com.example.organizador.ui.dao.DisciplinasDao
import com.example.organizador.ui.dialog.FormularioImagemDialog
import com.example.organizador.ui.extensions.tentaCarregarImagem
import com.example.organizador.ui.model.Disciplina
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    private var hora_inicial: Int? = null
    private var minuto_inicial: Int? = null

    private var hora_final: Int? = null
    private var minuto_final: Int? = null


    private lateinit var botaoswitchAlarme: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val imagem = binding.activityFormularioImagem
        imagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.activityFormularioImagem.tentaCarregarImagem(url)
                }
        }

        val botaoHoraInicial = binding.activityFormularioBotaoHoraInicial
        botaoHoraInicial.setOnClickListener {

            // val isSystem24Hour = is24HourFormat(this)
            //val formatoHora = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

            val picker = timePicker()

            picker.show(supportFragmentManager, "TAG")

            picker.addOnPositiveButtonClickListener {
                hora_inicial = picker.hour
                minuto_inicial = picker.minute

                var minutoCorrigo = minuto_inicial.toString()
                if (minuto_inicial!! < 10) {
                    minutoCorrigo = "0${minuto_inicial.toString()}"
                }
                val textobotao = "começamos as " + hora_inicial.toString() + ":" + minutoCorrigo
                botaoHoraInicial.text = textobotao

            }

        }
        val botaoHoraFinal = binding.activityFormularioBotaoHoraFinal
        botaoHoraFinal.setOnClickListener {

            val picker = timePicker()

            picker.show(supportFragmentManager, "TAG")

            picker.addOnPositiveButtonClickListener {

                hora_final = picker.hour
                minuto_final = picker.minute

                var minutoCorrigido = minuto_final.toString()
                if (minuto_final!! < 10) {
                    minutoCorrigido = "0${minuto_final.toString()}"
                }
                val textobotao = "terminamos as " + hora_final.toString() + ":" + minutoCorrigido
                botaoHoraFinal.text = textobotao
            }

        }

        botaoswitchAlarme = binding.activityFormularioSwitchAlarme
        botaoswitchAlarme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                Toast.makeText(this, "Alarme será criado", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Alarme não será criado", Toast.LENGTH_SHORT).show()
            }
        }

        configuraBotaoSalvar()

    }

    private fun timePicker(): MaterialTimePicker {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Selecione a hora")


            .build()
        return picker
    }


    private fun configuraBotaoSalvar() {
        val botaosalvar = binding.activityFormularioBotaoSalvar
        botaosalvar.setOnClickListener {

            val camponome = binding.activityFormularioEditTextNome
            val nome = camponome.text.toString()

            val campoDescricao = binding.activityFormularioEditTextDescricao
            val descricao = campoDescricao.text.toString()

            var horaInicial = (hora_inicial.toString() + ":" + minuto_inicial.toString())


            if (hora_inicial != null) {
                horaInicial = if (minuto_inicial!! < 10) {
                    (hora_inicial.toString() + ":0" + minuto_inicial.toString())
                } else
                    (hora_inicial.toString() + ":" + minuto_inicial.toString())
            }


            var horaFinal = "-"

            if (hora_final != null) {
                horaFinal = if (minuto_final!! < 10) {
                    (hora_final.toString() + ":0" + minuto_final.toString())
                } else
                    (hora_final.toString() + ":" + minuto_final.toString())
            }

            if ((camponome.text.toString().trim() == "") or (hora_inicial == null)
            ) {
                Toast.makeText(this, "Existem campo (s) vazio (s)", Toast.LENGTH_SHORT).show()

            } else {


                AlertDialog.Builder(this)
                    .setView(R.layout.dialog_formulario_confirma)
                    //.setTitle("titulo")
                    // .setMessage("mensagem")
                    .setPositiveButton("Confirmar") { _, _ ->


                        val disciplinanova = Disciplina(
                            nome = nome,
                            descricao = descricao,
                            hora_inicial = horaInicial,
                            hora_final = horaFinal,
                            imagem = url
                        )
                        val dao = DisciplinasDao()
                        dao.adiciona(disciplinanova)
                        Toast.makeText(this, "Disciplina Adicionada", Toast.LENGTH_SHORT).show()

                        if (botaoswitchAlarme.isChecked) {

                            val intentAdicionaAlarme = Intent(AlarmClock.ACTION_SET_ALARM)
                            intentAdicionaAlarme.putExtra(AlarmClock.EXTRA_HOUR, hora_inicial)
                            intentAdicionaAlarme.putExtra(AlarmClock.EXTRA_MINUTES, minuto_inicial)
                            intentAdicionaAlarme.putExtra(
                                AlarmClock.EXTRA_MESSAGE,
                                "Está na hora de estudar $nome"
                            )

                            startActivity(intentAdicionaAlarme)

                        }
                        finish()

                    }
                    .setNegativeButton("Cancelar") { _, _ -> }
                    .show()

            }
        }
    }
}


