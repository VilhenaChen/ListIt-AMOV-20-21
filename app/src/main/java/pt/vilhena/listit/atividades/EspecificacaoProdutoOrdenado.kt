package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_especificacao_produto.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class EspecificacaoProdutoOrdenado : Activity() {
    lateinit var dados : Dados
    var posicao : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_especificacao_produto_ordenado)
        dados=intent.getSerializableExtra("dados") as Dados
        posicao = intent.getIntExtra("posicaoProduto",0)

        textDesignacao.setText(dados.getListaProdutosOrdenados()[posicao].designacao)
        textMarca.setText(dados.getListaProdutosOrdenados()[posicao].marca)
        textCategoria.setText(dados.getListaProdutosOrdenados()[posicao].categoria)
        textQuantidade.setText(dados.getListaProdutosOrdenados()[posicao].quantidade.toString())
        textUnidade.setText(dados.getListaProdutosOrdenados()[posicao].unidade)
        textPreco.setText(dados.getListaProdutosOrdenados()[posicao].preco.toString())
        textNotas.setText(dados.getListaProdutosOrdenados()[posicao].notas)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerProdutosOrdenados::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

}