package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_notas_produto_compra.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class NotasProdutoCompraOrdenado : Activity() {
    lateinit var dados : Dados
    var posicaoProd : Int = 0
    var posicaoList : Int = 0
    var posicaoAux : Int = 0
    var designacao : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas_produto_compra_ordenado)

        dados=intent.getSerializableExtra("dados") as Dados
        posicaoProd = intent.getIntExtra("posicaoProduto",0)
        posicaoList = intent.getIntExtra("posicaoLista",0)

        editNotas.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].notas)
    }

    fun onClickBtnSave(view: View) {
        if(editNotas.text.toString() != dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].notas)
        {
            dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].notas = editNotas.text.toString()
            posicaoAux = dados.getArrayListas()[posicaoList].getProdutoComListaProdutosOrdenados(posicaoProd)
            dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoAux].notas = editNotas.text.toString()
        }
        val intent = Intent(this, EspecificacaoProdutoCompraOrdenado::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoProduto", posicaoProd)
        intent.putExtra("posicaoLista", posicaoList)
        startActivity(intent)
        finish()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, EspecificacaoProdutoCompraOrdenado::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoLista", posicaoList)
        intent.putExtra("posicaoProduto", posicaoProd)
        startActivity(intent)
        finish()
    }
}