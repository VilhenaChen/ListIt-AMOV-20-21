package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_remove_lista.*
import kotlinx.android.synthetic.main.activity_remove_unidade.*
import kotlinx.android.synthetic.main.entrada_lista.view.*
import kotlinx.android.synthetic.main.entrada_unidade_para_remover.view.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Lista
import pt.vilhena.listit.logica.Unidade

class RemoveLista : Activity() {
    lateinit var arrayListas : ArrayList<Lista>
    var listasARemover = ArrayList<String>()
    lateinit var dados : Dados
    var adapter: RemoveListasAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_lista)

        dados = intent.getSerializableExtra("dados") as Dados
        arrayListas = dados.getArrayListas()

        adapter = RemoveListasAdapter(this, arrayListas)

        grelhaRemoveListas.adapter = adapter

        grelhaRemoveListas.setOnItemClickListener { parent, view, position, id ->

            view.setBackgroundColor(Color.rgb(233, 211, 255))
            listasARemover.add(arrayListas[position].getNome())
        }
    }

    class RemoveListasAdapter : BaseAdapter {
        var arrayListas = ArrayList<Lista>()
        var context: Context? = null
        constructor(context: Context, arrayListas : ArrayList<Lista>) : super(){
            this.context=context
            this.arrayListas=arrayListas
        }

        override fun getCount(): Int {
            return arrayListas.size
        }

        override fun getItem(position: Int): Any {
            return arrayListas[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val lista = this.arrayListas[position]

            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var listaView = inflator.inflate(R.layout.entrada_lista, null)
            listaView.nomeLista.text = lista.getNome()

            return listaView
        }
    }

    fun onClickBtnTrash(view: View) {
        for (i in listasARemover.indices)
        {
            dados.removeListaComNome(listasARemover[i])
        }
        val intent = Intent(this, VerListas::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerListas::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}