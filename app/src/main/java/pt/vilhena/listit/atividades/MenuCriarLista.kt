package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class MenuCriarLista : Activity() {
    lateinit var dados : Dados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_criar_lista)

        dados=intent.getSerializableExtra("dados") as Dados
    }

    fun onClickBtnNovaLista(view: View) {
        dados.addLista()
        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
    fun onClickBtnListaImportada(view: View) {
        val intent = Intent(this, EscolheListaImportada::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}