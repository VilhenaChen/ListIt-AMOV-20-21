package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_especificacao_produto.*
import kotlinx.android.synthetic.main.activity_especificacao_produto.textCategoria
import kotlinx.android.synthetic.main.activity_especificacao_produto.textDesignacao
import kotlinx.android.synthetic.main.activity_especificacao_produto.textMarca
import kotlinx.android.synthetic.main.activity_especificacao_produto.textNotas
import kotlinx.android.synthetic.main.activity_especificacao_produto.textPreco
import kotlinx.android.synthetic.main.activity_especificacao_produto.textQuantidade
import kotlinx.android.synthetic.main.activity_especificacao_produto.textUnidade
import kotlinx.android.synthetic.main.activity_especificacao_produto_compra.*
import kotlinx.android.synthetic.main.entrada_produto_compra.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class EspecificacaoProdutoCompra : Activity() {
    lateinit var dados : Dados
    var posicaoProd : Int = 0
    var posicaoList : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_especificacao_produto_compra)

        dados=intent.getSerializableExtra("dados") as Dados
        posicaoProd = intent.getIntExtra("posicaoProduto",0)
        posicaoList = intent.getIntExtra("posicaoLista",0)

        Log.d("oi","cena 7 ${dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].designacao}")

        textDesignacao.setText(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].designacao)
        textMarca.setText(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].marca)
        textCategoria.setText(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].categoria)
        textQuantidade.setText(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].quantidade.toString())
        textUnidade.setText(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].unidade)
        textPreco.setText(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].preco.toString())
        textNotas.setText(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].notas)

        if(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Em aberto"){
           estado.setImageResource(R.drawable.ic_btn_square)
        }
        if(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Carrinho"){
            estado.setImageResource(R.drawable.ic_btn_in_cart)
        }
        if(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Comprado"){
            estado.setImageResource(R.drawable.ic_btn_bought)
        }

        layoutEstado.setOnClickListener { view ->
            if(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Em aberto"){
                dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado = "Carrinho"
                estado.setImageResource(R.drawable.ic_btn_in_cart)
            }
            else{
                if(dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Carrinho"){
                    dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado = "Comprado"
                    estado.setImageResource(R.drawable.ic_btn_bought)
                }
                else {
                    if (dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado == "Comprado") {
                        dados.getArrayListas()[posicaoList].getListaProdutos()[posicaoProd].estado = "Em aberto"
                        estado.setImageResource(R.drawable.ic_btn_square)
                    }
                }
            }
        }

        layoutNotas.setOnClickListener { view ->
            val intent = Intent(this, ListaCompra::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("posicaoLista", posicaoList)
            intent.putExtra("posicaoProd", posicaoProd)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ListaCompra::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoLista", posicaoList)
        startActivity(intent)
        finish()
    }
}