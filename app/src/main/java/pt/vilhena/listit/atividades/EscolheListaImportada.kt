package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_lista_importada.*
import kotlinx.android.synthetic.main.activity_ver_listas.*
import kotlinx.android.synthetic.main.entrada_lista.view.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Lista

class EscolheListaImportada : Activity() {
    lateinit var arrayListas: ArrayList<Lista>
    lateinit var dados: Dados
    var adapter: ListaImportadaAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_importada)

        dados = intent.getSerializableExtra("dados") as Dados
        arrayListas = dados.getArrayListas()

        adapter = ListaImportadaAdapter(this, arrayListas)

        grelhaListasImportatadas.adapter = adapter

        grelhaListasImportatadas.setOnItemClickListener { parent, view, position, id ->
            dados.addLista()
            dados.fazCopiaLista(position)
            val intent = Intent(this, NovaLista::class.java)
            intent.putExtra("dados", dados)
            startActivity(intent)
            finish()
        }
    }

    class ListaImportadaAdapter : BaseAdapter {
        var arrayListas = ArrayList<Lista>()
        var context: Context? = null

        constructor(context: Context, arrayListas: ArrayList<Lista>) : super() {
            this.context = context
            this.arrayListas = arrayListas
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

            var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var listaView = inflator.inflate(R.layout.entrada_lista, null)
            listaView.nomeLista.text = lista.getNome()

            return listaView
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MenuCriarLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}