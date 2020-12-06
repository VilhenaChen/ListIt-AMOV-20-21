package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_especificacao_produto.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class EspecificacaoProdutoPesquisado : Activity() {
    lateinit var dados : Dados
    var posicao : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_especificacao_produto_pesquisado)

        dados=intent.getSerializableExtra("dados") as Dados
        posicao = intent.getIntExtra("posicaoProduto",0)

        textDesignacao.setText(dados.getListaProdutosPesquisados()[posicao].designacao)
        textMarca.setText(dados.getListaProdutosPesquisados()[posicao].marca)
        textCategoria.setText(dados.getListaProdutosPesquisados()[posicao].categoria)
        textQuantidade.setText(dados.getListaProdutosPesquisados()[posicao].quantidade.toString())
        textUnidade.setText(dados.getListaProdutosPesquisados()[posicao].unidade)
        textPreco.setText(dados.getListaProdutosPesquisados()[posicao].preco.toString())
        textNotas.setText(dados.getListaProdutosPesquisados()[posicao].notas)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerProdutosPesquisados::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}