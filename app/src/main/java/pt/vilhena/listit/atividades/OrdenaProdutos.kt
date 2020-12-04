package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_ordena_produtos.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados


class OrdenaProdutos : Activity() {
    lateinit var dados : Dados
    lateinit var modo : String
    lateinit var ordem : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordena_produtos)

        val itemsOrdenaTop = arrayOf("Alfabeticamente", "Quantidade" , "Preço")
        val itemsOrdenaBottom = arrayOf("Crescente", "Decrescente")
        val adapterTop = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            itemsOrdenaTop
        )
        spinnerOrdenaTop.setAdapter(adapterTop)
        val adapterBottom = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            itemsOrdenaBottom
        )
        spinnerOrdenaBottom.setAdapter(adapterBottom)

        spinnerOrdenaTop.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val items = spinnerOrdenaTop.selectedItem.toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinnerOrdenaBottom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val items = spinnerOrdenaBottom.selectedItem.toString()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    fun OnClickBtnSearch(view: View) {}

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerProdutos::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}