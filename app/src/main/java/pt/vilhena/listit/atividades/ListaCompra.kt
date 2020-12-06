package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_lista_compra.*
import kotlinx.android.synthetic.main.activity_ver_produtos.*
import kotlinx.android.synthetic.main.entrada_lista.*
import kotlinx.android.synthetic.main.entrada_produto.view.*
import kotlinx.android.synthetic.main.entrada_produto.view.designacaoProdutos
import kotlinx.android.synthetic.main.entrada_produto.view.quantidadeProdutos
import kotlinx.android.synthetic.main.entrada_produto.view.unidadeProdutos
import kotlinx.android.synthetic.main.entrada_produto_compra.view.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class ListaCompra : Activity() {
    lateinit var listaProdutos : ArrayList<Produto>
    lateinit var dados : Dados
    var posicao : Int = 0
    var adapter: CompraAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lista_compra)
        dados = intent.getSerializableExtra("dados") as Dados
        posicao = intent.getIntExtra("posicaoLista",0)

        listaProdutos = dados.getArrayListas()[posicao].getListaProdutos()

        nomeListaCompra.setText(dados.getArrayListas()[posicao].getNome())

        adapter = CompraAdapter(this, listaProdutos)

        grelhaListaCompra.adapter = adapter

        grelhaListaCompra.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, EspecificacaoProduto::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("posicaoProduto", position)
            startActivity(intent)
            finish()
        }
    }

    class CompraAdapter : BaseAdapter {
        var listaProdutos = ArrayList<Produto>()
        var context: Context? = null
        constructor(context: Context, listaProdutos: ArrayList<Produto>) : super(){
            this.context=context
            this.listaProdutos=listaProdutos
        }

        override fun getCount(): Int {
            return listaProdutos.size
        }

        override fun getItem(position: Int): Any {
            return listaProdutos[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val produto = this.listaProdutos[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var produtoView = inflator.inflate(R.layout.entrada_produto_compra, null)
            produtoView.designacaoProdutos.text = produto.designacao
            produtoView.quantidadeProdutos.text = produto.quantidade.toString()
            produtoView.unidadeProdutos.text = produto.unidade
            if(produto.estado == "Em aberto"){
                produtoView.estado.setImageResource(R.drawable.ic_btn_square)
            }
            if(produto.estado == "Carrinho"){
                produtoView.estado.setImageResource(R.drawable.ic_btn_in_cart)
            }
            if(produto.estado == "Comprado"){
                produtoView.estado.setImageResource(R.drawable.ic_btn_bought)
            }

            return produtoView
        }
    }

    fun OnClickBtnSearch(view: View) {
        val intent = Intent(this, PesquisaProdutosCompra::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoProduto", posicao)
        startActivity(intent)
        finish()
    }

    fun onClickBtnOrder(view: View) {
        val intent = Intent(this, OrdenaProdutosCompra::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoProduto", posicao)
        startActivity(intent)
        finish()
    }

    fun onBtnEnd(view: View) {

    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerListas::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }


}