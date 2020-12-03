package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Color.rgb
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_remove_unidade.*
import kotlinx.android.synthetic.main.entrada_unidade.view.*
import kotlinx.android.synthetic.main.entrada_unidade_para_remover.*
import kotlinx.android.synthetic.main.entrada_unidade_para_remover.view.*
import org.w3c.dom.Text
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Unidade

var ABS ="OI"

class RemoveUnidade : Activity() {
    lateinit var listaUnidades : ArrayList<Unidade>
    var unidadesARemover = ArrayList<String>()
    lateinit var dados : Dados
    var adapter: RemoveUnidadesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_unidade)

        dados = intent.getSerializableExtra("dados") as Dados
        listaUnidades = dados.getListaUnidades()

        adapter = RemoveUnidadesAdapter(this, listaUnidades)

        grelhaRemoveUnidades.adapter = adapter

        grelhaRemoveUnidades.setOnItemClickListener { parent, view, position, id ->

            view.setBackgroundColor(rgb(233, 211, 255))
            unidadesARemover.add(listaUnidades[position].designacao.toString())
            Log.i(ABS,"AQUIadasd")
        }
    }


    class RemoveUnidadesAdapter : BaseAdapter {
        var listaUnidades = ArrayList<Unidade>()
        var context: Context? = null
        constructor(context: Context, listaUnidades: ArrayList<Unidade>) : super(){
            this.context=context
            this.listaUnidades=listaUnidades
        }

        override fun getCount(): Int {
            return listaUnidades.size
        }

        override fun getItem(position: Int): Any {
            return listaUnidades[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val unidade = this.listaUnidades[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var unidadeView = inflator.inflate(R.layout.entrada_unidade_para_remover, null)
            unidadeView.designacaoRemoveUnidades.text = unidade.designacao

            return unidadeView
        }
    }

    fun onClickBtnMinus(view: View) {
        for (i in unidadesARemover.indices) {
            dados.removeUnidade(unidadesARemover[i])
        }
        val intent = Intent(this, VerUnidades::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}