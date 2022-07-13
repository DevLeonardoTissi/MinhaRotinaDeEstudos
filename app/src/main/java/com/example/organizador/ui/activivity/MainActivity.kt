package com.example.organizador.ui.activivity


import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.organizador.databinding.ActivityMainBinding
import com.example.organizador.databinding.DialogEditaperfilBinding
import com.example.organizador.ui.model.Usuario

class MainActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1

    private val meuUsuario = Usuario()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val botao_edita_perfil = binding.activityMainImagebuttonEditar
        botao_edita_perfil.setOnClickListener {
            configuraperfil()

        }


        val botaoverlista = binding.activityMainButtonVerLista
        botaoverlista.setOnClickListener {
            val intent_troca_tela_lista = Intent(this, ListaActivity::class.java)
            startActivity(intent_troca_tela_lista)

        }

        val botaoadicionaritem = binding.activityMainButtonAdicionarItem
        botaoadicionaritem.setOnClickListener {
            val intent_troca_tela_formulario = Intent(this, FormularioActivity::class.java)
            startActivity(intent_troca_tela_formulario)
        }

    }

    private fun configuraperfil(
        imagem: Bitmap? = null, nomeUsuarioDigitado: String? = null
    ) {
        val bindingDialogEditaPerfil = DialogEditaperfilBinding.inflate(layoutInflater)
        val botaotirafoto = bindingDialogEditaPerfil.dialogEditaPerfilBotaoTirarFoto


        if (imagem != null) {
            bindingDialogEditaPerfil.dialogEditaPerfilImagemImageView.setImageBitmap(imagem)
        }

        // if(nomeUsuarioDigitado != null){
        //   bindingDialogEditaPerfil.dialogEditaPerfilEditTextNome.setText(nomeUsuarioDigitado)
        // }

        val chamaAlertDialog = AlertDialog.Builder(this)
            .setView(bindingDialogEditaPerfil.root)
            .setPositiveButton("Confirmar") { _, _ ->


                if (bindingDialogEditaPerfil.dialogEditaPerfilEditTextNome.text.toString()
                        .trim() != ""
                ) {
                    meuUsuario.nome =
                        bindingDialogEditaPerfil.dialogEditaPerfilEditTextNome.text.toString()
                    val nomeUsuario = meuUsuario.nome
                    binding.activityMainTextViewSaudacao.text =
                        "Olá, $nomeUsuario"
                }
                if (imagem != null) {
                    meuUsuario.foto = (imagem)
                    binding.activityMainImageViewPrincipal.setImageBitmap(meuUsuario.foto)
                }

            }
            .setNegativeButton("Cancelar") { _, _ ->

            }
            .show()
        botaotirafoto.setOnClickListener {

            abrirCamera()
            chamaAlertDialog.dismiss()
        }

        val botaobuscarfoto = bindingDialogEditaPerfil.dialogEditaPerfilBotaoCarregar
        botaobuscarfoto.setOnClickListener {
            abrirGaleria()
            chamaAlertDialog.dismiss()
        }

    }

    private fun abrirGaleria() {


        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.INTERNAL_CONTENT_URI
        )
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 2)


    }


    private fun MainActivity.abrirCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imagemcapturada = data?.extras?.get("data") as Bitmap
            configuraperfil(imagemcapturada)
        }

        if (requestCode == 2) {
            val uri = data?.data
            if  (uri!= null){
                val imagemcapturada = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                configuraperfil(imagemcapturada)
            }
        }
    }
}









