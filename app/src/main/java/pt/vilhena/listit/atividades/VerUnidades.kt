package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_ver_unidades.*
import kotlinx.android.synthetic.main.entrada_unidade.view.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Unidade


class VerUnidades : Activity() {

    lateinit var listaUnidades : ArrayList<Unidade>
    lateinit var dados : Dados
    var adapter: UnidadesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_unidades)

        dados = intent.getSerializableExtra("dados") as Dados
        listaUnidades = dados.getListaUnidades()

        adapter = UnidadesAdapter(this, listaUnidades)

        grelhaUnidades.adapter = adapter
    }

    class UnidadesAdapter : BaseAdapter {
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
            var unidadeView = inflator.inflate(R.layout.entrada_unidade, null)
            unidadeView.designacaoUnidades.text = unidade.designacao
            unidadeView.abreviaturaUnidades.text = unidade.abreviatura

            return unidadeView
        }
    }

    fun onClickBtnAdd(view: View) {
        val intent = Intent(this, NovaUnidade::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
    }

    fun onClickBtnMinus(view: View) {
        //Ir para Remove Unidade
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}


