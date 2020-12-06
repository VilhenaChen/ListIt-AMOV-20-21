package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_especificacao_produto.*
import kotlinx.android.synthetic.main.activity_especificacao_produto.textCategoria
import kotlinx.android.synthetic.main.activity_especificacao_produto.textDesignacao
import kotlinx.android.synthetic.main.activity_especificacao_produto.textMarca
import kotlinx.android.synthetic.main.activity_especificacao_produto.textNotas
import kotlinx.android.synthetic.main.activity_especificacao_produto.textPreco
import kotlinx.android.synthetic.main.activity_especificacao_produto.textQuantidade
import kotlinx.android.synthetic.main.activity_especificacao_produto.textUnidade
import kotlinx.android.synthetic.main.activity_especificacao_produto_compra.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class EspecificacaoProdutoCompraPesquisado : Activity() {
    lateinit var dados : Dados
    var posicaoProd : Int = 0
    var posicaoList : Int = 0
    var posicaoAux : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_especificacao_produto_compra_pesquisado)

        dados=intent.getSerializableExtra("dados") as Dados
        posicaoProd = intent.getIntExtra("posicaoProduto",0)
        posicaoList = intent.getIntExtra("posicaoLista",0)

        textDesignacao.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].designacao)
        textMarca.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].marca)
        textCategoria.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].categoria)
        textQuantidade.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].quantidade.toString())
        textUnidade.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].unidade)
        textPreco.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].preco.toString())
        textNotas.setText(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].notas)

        if(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].estado == "Em aberto"){
            estado.setImageResource(R.drawable.ic_btn_square)
        }
        if(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].estado == "Carrinho"){
            estado.setImageResource(R.drawable.ic_btn_in_cart)
        }
        if(dados.getArrayListas()[posicaoList].getListaProdutosOrdenados()[posicaoProd].estado == "Comprado"){
            estado.setImageResource(R.drawable.ic_btn_bought)
        }

        layoutEstado.setOnClickListener { view ->
            if(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Em aberto"){
                dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado = "Carrinho"
                posicaoAux = dados.getArrayListas()[posicaoList].getProdutoComListaProdutosPesquisados(posicaoProd)
                dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoAux].estado = "Carrinho"
                estado.setImageResource(R.drawable.ic_btn_in_cart)
            }
            else {
                if (dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Carrinho") {
                    dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado = "Comprado"
                    posicaoAux = dados.getArrayListas()[posicaoList].getProdutoComListaProdutosPesquisados(posicaoProd)
                    dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoAux].estado = "Comprado"
                    estado.setImageResource(R.drawable.ic_btn_bought)
                }
                else {
                    if (dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Comprado") {
                        dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado = "Em aberto"
                        posicaoAux = dados.getArrayListas()[posicaoList].getProdutoComListaProdutosPesquisados(posicaoProd)
                        dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoAux].estado = "Em aberto"
                        estado.setImageResource(R.drawable.ic_btn_square)
                    }
                }
            }
        }

        layoutNotas.setOnClickListener { view ->
            val intent = Intent(this, ProdutoCompraPesquisado::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("posicaoLista", posicaoList)
            intent.putExtra("posicaoProd", posicaoProd)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ProdutoCompraPesquisado::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoLista", posicaoList)
        startActivity(intent)
        finish()
    }
}