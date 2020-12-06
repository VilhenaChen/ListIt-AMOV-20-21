package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_remove_produto_lista.*
import kotlinx.android.synthetic.main.activity_remove_unidade.*
import kotlinx.android.synthetic.main.entrada_produto.view.*
import kotlinx.android.synthetic.main.entrada_unidade_para_remover.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto
import pt.vilhena.listit.logica.Unidade

class RemoveProdutoLista : Activity() {
    lateinit var listaProdutos : ArrayList<Produto>
    var produtosARemover = ArrayList<String>()
    lateinit var dados : Dados
    var adapter: RemoveProdutosAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_produto_lista)

        dados = intent.getSerializableExtra("dados") as Dados
        listaProdutos = dados.getArrayListas().last().getListaProdutos()

        adapter = RemoveProdutosAdapter(this, listaProdutos)

        grelhaRemoveProdutos.adapter = adapter

        grelhaRemoveProdutos.setOnItemClickListener { parent, view, position, id ->

            view.setBackgroundColor(Color.rgb(233, 211, 255))
            produtosARemover.add(listaProdutos[position].designacao.toString())
        }
    }


    class RemoveProdutosAdapter : BaseAdapter {
        var listaProdutos = ArrayList<Produto>()
        var context: Context? = null
        constructor(context: Context, listaProdutos : ArrayList<Produto>) : super(){
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

    fun onClickBtnMinus(view: View) {
        for (i in produtosARemover.indices) {
            dados.getArrayListas().last().removeProduto(produtosARemover[i])
        }
        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}