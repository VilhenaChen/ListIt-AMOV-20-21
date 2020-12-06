package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nova_unidade.*
import kotlinx.android.synthetic.main.activity_novo_produto.*
import kotlinx.android.synthetic.main.activity_ordena_produtos.*
import pt.vilhena.listit.MainActivity
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados
import pt.vilhena.listit.logica.Produto

class NovoProduto : Activity() {
    lateinit var dados : Dados
    var designacao : String = ""
    var marca : String = ""
    var categoria : String = ""
    var quantidade : Int = 0
    var unidade : String = ""
    var preco : Float = 0F
    var notas : String = ""
    var listaAbreviaturasUnidades = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_produto)

        dados=intent.getSerializableExtra("dados") as Dados

        for(i in dados.getListaUnidades().indices)
        {
            listaAbreviaturasUnidades.add(dados.getListaUnidades()[i].abreviatura.toString())
        }

        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listaAbreviaturasUnidades
        )
        spinnerUnidades.setAdapter(adapter)

        spinnerUnidades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val items = spinnerUnidades.selectedItem.toString()

                unidade = listaAbreviaturasUnidades[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }


    }

    fun onClickBtnAdd(view: View) {
        if(editDesignacaoProd.text.isEmpty())
        {
            Toast.makeText(this, R.string.AbreviaturaFalta ,Toast.LENGTH_LONG).show()
            return
        }
        else
        {
            designacao = editDesignacaoProd.text.toString().capitalize()
        }
        if(editQuantidade.text.isEmpty())
        {
            Toast.makeText(this, R.string.QuantidadeFalta ,Toast.LENGTH_LONG).show()
            return
        }
        else
        {
            quantidade = editQuantidade.text.toString().toInt()
        }
        if(unidade == "")
        {
            Toast.makeText(this, R.string.UnidadeFalta ,Toast.LENGTH_LONG).show()
            return
        }

        if(!editMarca.text.isEmpty())
        {
            marca = editMarca.text.toString().capitalize()
        }

        if(!editCategoria.text.isEmpty())
        {
            categoria = editCategoria.text.toString().capitalize()
        }

        if(!editPreco.text.isEmpty())
        {
            preco = editPreco.text.toString().toFloat()
        }

        if(!editNotas.text.isEmpty())
        {
            notas = editNotas.text.toString()
        }

        dados.addProduto(designacao,marca,quantidade,unidade,categoria,notas,preco)
        dados.getArrayListas().last().addProduto(dados.getListaProdutos().last())

        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MenuAdicionarProduto::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }
}