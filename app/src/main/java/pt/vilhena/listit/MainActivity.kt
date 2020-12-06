package pt.vilhena.listit

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import pt.vilhena.listit.atividades.MenuCriarLista
import pt.vilhena.listit.atividades.VerListas
import pt.vilhena.listit.atividades.VerProdutos
import pt.vilhena.listit.atividades.VerUnidades
import pt.vilhena.listit.logica.Dados

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
            if(savedInstanceState != null)
            {
                dados=savedInstanceState.getSerializable("DADOS") as Dados
            }
            else{
                dados.addUnidade("Unidade", "uni")
                dados.addUnidade("Kilogramas", "Kg")
                dados.addProduto("Pão","Branca",10,"uni","","apenas se não estiver rijo",0F)
                dados.addProduto("Arroz","Cigala",3,"Kg","Cereiais","Apenas do Carolino",0F)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun onBtnCriarLista(view: View) {
        val intent = Intent(this, MenuCriarLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
    fun onBtnListas(view: View) {
        val intent = Intent(this, VerListas::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
    fun onBtnVerProdutos(view: View) {
        val intent = Intent(this, VerProdutos::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
    fun onBtnVerUnidades(view: View) {
        val intent = Intent(this, VerUnidades::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("DADOS", dados)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        dados=savedInstanceState.getSerializable("DADOS") as Dados
        super.onRestoreInstanceState(savedInstanceState)
    }

}