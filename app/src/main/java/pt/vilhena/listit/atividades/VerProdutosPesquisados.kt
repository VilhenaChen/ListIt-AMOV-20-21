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
import kotlinx.android.synthetic.main.activity_ver_produtos.*
import kotlinx.android.synthetic.main.activity_ver_produtos_pesquisados.*
import kotlinx.android.synthetic.main.entrada_produto.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class VerProdutosPesquisados : Activity() {
    lateinit var listaProdutosPesquisados : ArrayList<Produto>
    lateinit var dados : Dados
    var adapter: ProdutosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_produtos_pesquisados)

        dados = intent.getSerializableExtra("dados") as Dados
        listaProdutosPesquisados = dados.getListaProdutosPesquisados()

        adapter = ProdutosAdapter(this, listaProdutosPesquisados)

        grelhaProdutosPesquisados.adapter = adapter
    }

    class ProdutosAdapter : BaseAdapter {
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
            var produtoView = inflator.inflate(R.layout.entrada_produto, null)

            produtoView.designacaoProdutos.text = produto.designacao
            produtoView.quantidadeProdutos.text = produto.quantidade.toString()
            produtoView.unidadeProdutos.text = produto.unidade

            return produtoView
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerProdutos::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}