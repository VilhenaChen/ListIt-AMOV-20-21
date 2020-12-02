package pt.vilhena.listit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import pt.vilhena.listit.atividades.VerUnidades
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Unidade

class MainActivity : Activity() {

    var dados = Dados()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(intent.hasExtra("dados"))
        {
            dados=intent.getSerializableExtra("dados") as Dados
        }
        else
        {
            dados.addUnidade("Unidade", "uni")
            dados.addUnidade("Kilogramas", "Kg")
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun onBtnCriarLista(view: View) {
        //Ir para Menu Criar Lista
    }
    fun onBtnListas(view: View) {
        //Ir para Historico Listas
    }
    fun onBtnVerProdutos(view: View) {
        //Ir para Ver Prodtos
    }
    fun onBtnVerUnidades(view: View) {
        val intent = Intent(this, VerUnidades::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

}