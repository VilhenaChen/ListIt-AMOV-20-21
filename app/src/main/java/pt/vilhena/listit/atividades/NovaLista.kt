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
import kotlinx.android.synthetic.main.activity_nova_lista.*
import kotlinx.android.synthetic.main.activity_ver_produtos.*
import kotlinx.android.synthetic.main.entrada_produto.view.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class NovaLista : Activity() {
    var listaProdutos = ArrayList<Produto>()
    lateinit var dados : Dados
    var adapter: ProdutosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_lista)

        dados = intent.getSerializableExtra("dados") as Dados


        listaProdutos = dados.getArrayListas().last().getListaProdutos()


        adapter = ProdutosAdapter(this, listaProdutos)

        grelhaProdutosLista.adapter = adapter

        grelhaProdutosLista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, EditaProduto::class.java)
            intent.putExtra("dados", dados)
            intent.putExtra("posicaoProduto", position)
            startActivity(intent)
            finish()
        }
    }

    class ProdutosAdapter : BaseAdapter {
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

    fun onClickBtnMinus(view: View) {
        val intent = Intent(this, RemoveProdutoLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    fun onClickBtnAdd(view: View) {
        val intent = Intent(this, MenuAdicionarProduto::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    fun onClickBtnSave(view: View) {
        super.onBackPressed()
        val intent = Intent(this, GuardarLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        dados.removeLista(dados.getArrayListas().lastIndex)
        super.onBackPressed()
        val intent = Intent(this, MenuCriarLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}