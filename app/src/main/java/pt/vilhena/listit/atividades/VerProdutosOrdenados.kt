package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_ver_produtos.*
import kotlinx.android.synthetic.main.activity_ver_produtos_ordenados.*
import kotlinx.android.synthetic.main.activity_ver_produtos_pesquisados.*
import kotlinx.android.synthetic.main.entrada_produto.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class VerProdutosOrdenados : Activity() {
    lateinit var listaProdutosOrdenados : ArrayList<Produto>
    lateinit var dados : Dados
    var adapter: ProdutosAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_produtos_ordenados)

        dados = intent.getSerializableExtra("dados") as Dados
        listaProdutosOrdenados = dados.getListaProdutosOrdenados()

        adapter = ProdutosAdapter(this, listaProdutosOrdenados)

        grelhaProdutosOrdenados.adapter = adapter

        grelhaProdutosOrdenados.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, EspecificacaoProdutoOrdenado::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("posicaoProduto", position)
            startActivity(intent)
            finish()
        }
    }

    class ProdutosAdapter : BaseAdapter {
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
            var produtoView = inflator.inflate(R.layout.entrada_produto, null)

            produtoView.designacaoProdutos.text = produto.designacao
            produtoView.quantidadeProdutos.text = produto.quantidade.toString()
            produtoView.unidadeProdutos.text = produto.unidade

            return produtoView
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        dados.removeProdutosOrdenados()
        val intent = Intent(this, VerProdutos::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}