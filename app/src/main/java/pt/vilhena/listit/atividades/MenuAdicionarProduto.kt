package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class MenuAdicionarProduto : Activity() {
    lateinit var dados : Dados
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_adicionar_produto)
        dados=intent.getSerializableExtra("dados") as Dados
    }

    fun onClickBtnNovoProduto(view: View) {
        val intent = Intent(this, NovoProduto::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
    
    fun onClickBtnProdutoExistente(view: View) {
        val intent = Intent(this, ProdutoExistente::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }


}