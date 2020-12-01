package pt.vilhena.listit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import pt.vilhena.listit.atividades.VerUnidades
import pt.vilhena.listit.logica.Dados

class MainActivity : Activity() {
    val dados = Dados()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
        startActivity(intent)
    }


}