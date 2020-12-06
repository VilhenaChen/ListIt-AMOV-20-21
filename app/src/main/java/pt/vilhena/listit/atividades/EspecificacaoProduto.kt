package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_especificacao_produto.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class EspecificacaoProduto : Activity() {
    lateinit var dados : Dados
    var posicao : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_especificacao_produto)

        dados=intent.getSerializableExtra("dados") as Dados
        posicao = intent.getIntExtra("posicaoProduto",0)

        textDesignacao.setText(dados.getListaProdutos()[posicao].designacao)
        textMarca.setText(dados.getListaProdutos()[posicao].marca)
        textCategoria.setText(dados.getListaProdutos()[posicao].categoria)
        textQuantidade.setText(dados.getListaProdutos()[posicao].quantidade.toString())
        textUnidade.setText(dados.getListaProdutos()[posicao].unidade)
        textPreco.setText(dados.getListaProdutos()[posicao].preco.toString())
        textNotas.setText(dados.getListaProdutos()[posicao].notas)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerProdutos::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}