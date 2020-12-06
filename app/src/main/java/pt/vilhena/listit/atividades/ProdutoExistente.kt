package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_produto_existente.*
import kotlinx.android.synthetic.main.entrada_produto.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class ProdutoExistente : Activity() {
    lateinit var listaProdutos : ArrayList<Produto>
    lateinit var dados : Dados
    var adapter: ProdutosExistentesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_existente)

        dados=intent.getSerializableExtra("dados") as Dados

        listaProdutos = dados.getListaProdutos()

        adapter = ProdutosExistentesAdapter(this, listaProdutos)

        grelhaProdutosExistentes.adapter = adapter

        grelhaProdutosExistentes.setOnItemClickListener { parent, view, position, id ->
            dados.getArrayListas().last().addProduto(dados.getListaProdutos()[position])
            val intent = Intent(this, NovaLista::class.java)
            intent.putExtra("dados", dados)
            startActivity(intent)
            finish()
        }

    }

    class ProdutosExistentesAdapter : BaseAdapter {
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
            var produtoView = inflator.inflate(R.layout.entrada_produto, null)
            produtoView.designacaoProdutos.text = produto.designacao
            produtoView.quantidadeProdutos.text = produto.quantidade.toString()
            produtoView.unidadeProdutos.text = produto.unidade

            return produtoView
        }
    }

    fun onClickBtnSearch(view: View) {
        super.onBackPressed()
        val intent = Intent(this, PesquisaProdutosExistentes::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MenuAdicionarProduto::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}