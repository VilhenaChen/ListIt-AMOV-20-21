package pt.vilhena.listit.atividades

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_ordena_produtos.*
import pt.vilhena.listit.R


class OrdenaProdutos : Activity() {

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
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View?,
                arg2: Int,
                arg3: Long
            ) {
                // Do what you want
                val items = spinnerOrdenaTop.selectedItem.toString()

            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
        spinnerOrdenaBottom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View?,
                arg2: Int,
                arg3: Long
            ) {
                // Do what you want
                val items = spinnerOrdenaBottom.selectedItem.toString()

            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }
    }

    fun OnClickBtnSearch(view: View) {}
}