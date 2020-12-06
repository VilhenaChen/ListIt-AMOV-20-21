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
    var modo : Int = 0
    var ordem : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordena_produtos)

        dados = intent.getSerializableExtra("dados") as Dados

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

                modo=position;

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        spinnerOrdenaBottom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val items = spinnerOrdenaBottom.selectedItem.toString()

                ordem=position;

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

    }

    fun OnClickBtnCheck(view: View) {
        dados.ordenaProdutos(modo,ordem)
        val intent = Intent(this, VerProdutosOrdenados::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, VerProdutos::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

}