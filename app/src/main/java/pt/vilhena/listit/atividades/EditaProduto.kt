package pt.vilhena.listit.atividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_especificacao_produto.*
import kotlinx.android.synthetic.main.activity_nova_unidade.*
import kotlinx.android.synthetic.main.activity_novo_produto.*
import pt.vilhena.listit.R
import pt.vilhena.listit.logica.Dados

class EditaProduto : Activity() {
    var posicao : Int = 0
    var posicaoProduto : Int = 0
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
        setContentView(R.layout.activity_edita_produto)

        dados=intent.getSerializableExtra("dados") as Dados
        posicao = intent.getIntExtra("posicaoProduto",0)

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

        editDesignacaoProd.setText(dados.getArrayListas().last().getListaProdutos()[posicao].designacao)
        editMarca.setText(dados.getArrayListas().last().getListaProdutos()[posicao].marca)
        editCategoria.setText(dados.getArrayListas().last().getListaProdutos()[posicao].categoria)
        editQuantidade.setText(dados.getArrayListas().last().getListaProdutos()[posicao].quantidade.toString())
        editPreco.setText(dados.getArrayListas().last().getListaProdutos()[posicao].preco.toString())
        editNotas.setText(dados.getArrayListas().last().getListaProdutos()[posicao].notas)

        spinnerUnidades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val items = spinnerUnidades.selectedItem.toString()

                unidade = listaAbreviaturasUnidades[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

    fun onClickBtnSave(view: View) {
        posicaoProduto = dados.getProduto(dados.getArrayListas().last().getListaProdutos()[posicao].designacao.toString(), dados.getArrayListas().last().getListaProdutos()[posicao].marca.toString(),
            dados.getArrayListas().last().getListaProdutos()[posicao].quantidade, dados.getArrayListas().last().getListaProdutos()[posicao].unidade.toString(),
            dados.getArrayListas().last().getListaProdutos()[posicao].categoria.toString(),dados.getArrayListas().last().getListaProdutos()[posicao].notas.toString(),
            dados.getArrayListas().last().getListaProdutos()[posicao].preco)

        if(editDesignacaoProd.text.isEmpty())
        {
            Toast.makeText(this, "Indique uma Designação" , Toast.LENGTH_LONG).show()
            return
        }
        else
        {
            if(editDesignacaoProd.text.toString() != dados.getListaProdutos()[posicaoProduto].designacao){
                dados.getListaProdutos()[posicaoProduto].designacao = editDesignacaoProd.text.toString().capitalize()
                dados.getArrayListas().last().getListaProdutos()[posicao] = dados.getListaProdutos()[posicaoProduto]
            }
        }
        if(editQuantidade.text.isEmpty())
        {
            Toast.makeText(this, "Indique uma Quantidade" , Toast.LENGTH_LONG).show()
            return
        }
        else
        {
            if(editQuantidade.text.toString().toInt() != dados.getListaProdutos()[posicaoProduto].quantidade){
                dados.getListaProdutos()[posicaoProduto].quantidade = editDesignacaoProd.text.toString().toInt()
                dados.getArrayListas().last().getListaProdutos()[posicao] = dados.getListaProdutos()[posicaoProduto]
            }
        }
        if(unidade == "")
        {
            Toast.makeText(this, "Escolha uma Unidade" , Toast.LENGTH_LONG).show()
            return
        }
        else
        {
            if (unidade!= dados.getListaProdutos()[posicaoProduto].unidade){
                dados.getListaProdutos()[posicaoProduto].unidade = unidade.capitalize()
                dados.getArrayListas().last().getListaProdutos()[posicao] = dados.getListaProdutos()[posicaoProduto]
            }
        }

        if(!editMarca.text.isEmpty())
        {
            if(editMarca.text.toString() != dados.getListaProdutos()[posicaoProduto].marca){
                dados.getListaProdutos()[posicaoProduto].marca = editMarca.text.toString().capitalize()
                dados.getArrayListas().last().getListaProdutos()[posicao] = dados.getListaProdutos()[posicaoProduto]
            }
        }

        if(!editCategoria.text.isEmpty())
        {
            if(editCategoria.text.toString() != dados.getListaProdutos()[posicaoProduto].categoria){
                dados.getListaProdutos()[posicaoProduto].categoria = editCategoria.text.toString().capitalize()
                dados.getArrayListas().last().getListaProdutos()[posicao] = dados.getListaProdutos()[posicaoProduto]
            }
        }

        if(!editPreco.text.isEmpty())
        {
            if(editPreco.text.toString().toFloat() != dados.getListaProdutos()[posicaoProduto].preco){
                dados.getListaProdutos()[posicaoProduto].preco = editDesignacaoProd.text.toString().toFloat()
                dados.getArrayListas().last().getListaProdutos()[posicao] = dados.getListaProdutos()[posicaoProduto]
            }
        }

        if(!editNotas.text.isEmpty())
        {
            if(editNotas.text.toString() != dados.getListaProdutos()[posicaoProduto].notas){
                dados.getListaProdutos()[posicaoProduto].notas = editNotas.text.toString()
                dados.getArrayListas().last().getListaProdutos()[posicao] = dados.getListaProdutos()[posicaoProduto]
            }
        }

        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NovaLista::class.java)
        intent.putExtra("dados", dados)
        startActivity(intent)
        finish()
    }

}