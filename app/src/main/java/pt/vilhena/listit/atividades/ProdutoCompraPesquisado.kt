package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_produto_compra_pesquisado.*
import kotlinx.android.synthetic.main.entrada_produto.view.designacaoProdutos
import kotlinx.android.synthetic.main.entrada_produto.view.quantidadeProdutos
import kotlinx.android.synthetic.main.entrada_produto.view.unidadeProdutos
import kotlinx.android.synthetic.main.entrada_produto_compra.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class ProdutoCompraPesquisado : Activity() {
    lateinit var listaProdutosPesquisados : ArrayList<Produto>
    lateinit var dados : Dados
    var adapter: ProdutosCompraPesquisadosAdapter? = null
    var posicao : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_compra_pesquisado)

        dados = intent.getSerializableExtra("dados") as Dados

        posicao = intent.getIntExtra("posicaoLista",0)

        listaProdutosPesquisados = dados.getArrayListas()[posicao].getListaProdutosPesquisados()

        adapter = ProdutosCompraPesquisadosAdapter(this, listaProdutosPesquisados)

        nomeLista.setText(dados.getArrayListas()[posicao].getNome())

        grelhaProdutosCompraPesquisados.adapter=adapter

        grelhaProdutosCompraPesquisados.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, EspecificacaoProdutoCompraPesquisado::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("posicaoLista", posicao)
            intent.putExtra("posicaoProduto", position)
            startActivity(intent)
            finish()
        }
    }

    class ProdutosCompraPesquisadosAdapter : BaseAdapter {
        var listaProdutosPesquisados = ArrayList<Produto>()
        var context: Context? = null
        constructor(context: Context, listaProdutosPesquisados: ArrayList<Produto>) : super(){
            this.context=context
            this.listaProdutosPesquisados=listaProdutosPesquisados
        }

        override fun getCount(): Int {
            return listaProdutosPesquisados.size
        }

        override fun getItem(position: Int): Any {
            return listaProdutosPesquisados[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val produto = this.listaProdutosPesquisados[position]

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

    override fun onBackPressed() {
        super.onBackPressed()
        dados.getArrayListas().last().removeProdutosPesquisados()
        val intent = Intent(this, ListaCompra::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoLista", posicao)
        startActivity(intent)
        finish()
    }
}