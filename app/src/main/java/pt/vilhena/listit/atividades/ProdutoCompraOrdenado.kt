package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_produto_compra_ordenado.*
import kotlinx.android.synthetic.main.activity_ver_produtos_ordenados.*
import kotlinx.android.synthetic.main.entrada_produto.view.*
import kotlinx.android.synthetic.main.entrada_produto.view.designacaoProdutos
import kotlinx.android.synthetic.main.entrada_produto.view.quantidadeProdutos
import kotlinx.android.synthetic.main.entrada_produto.view.unidadeProdutos
import kotlinx.android.synthetic.main.entrada_produto_compra.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class ProdutoCompraOrdenado : Activity() {
    lateinit var listaProdutosOrdenados : ArrayList<Produto>
    lateinit var dados : Dados
    var adapter: ProdutosCompraOrdenadosAdapter? = null
    var posicao : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_compra_ordenado)

        dados = intent.getSerializableExtra("dados") as Dados
        listaProdutosOrdenados = dados.getArrayListas()[posicao].getListaProdutosOrdenados()
        posicao = intent.getIntExtra("posicaoLista",0)

        nomeLista.setText(dados.getArrayListas()[posicao].getNome())

        adapter = ProdutosCompraOrdenadosAdapter(this, listaProdutosOrdenados)

        grelhaProdutosCompraOrdenados.adapter = adapter

        grelhaProdutosCompraOrdenados.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, EspecificacaoProdutoCompraOrdenado::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("posicaoLista", posicao)
            intent.putExtra("posicaoProduto", position)
            startActivity(intent)
            finish()
        }
    }

    class ProdutosCompraOrdenadosAdapter : BaseAdapter {
        var listaProdutosOrdenados = ArrayList<Produto>()
        var context: Context? = null
        constructor(context: Context, listaProdutosOrdenados: ArrayList<Produto>) : super(){
            this.context=context
            this.listaProdutosOrdenados=listaProdutosOrdenados
        }

        override fun getCount(): Int {
            return listaProdutosOrdenados.size
        }

        override fun getItem(position: Int): Any {
            return listaProdutosOrdenados[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val produto = this.listaProdutosOrdenados[position]

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
        dados.getArrayListas()[posicao].removeProdutosOrdenados()
        val intent = Intent(this, ListaCompra::class.java)
        intent.putExtra("dados", dados)
        intent.putExtra("posicaoLista", posicao)
        startActivity(intent)
        finish()
    }
}